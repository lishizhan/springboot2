package com.example.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.dao.*;
import com.example.mybatisplus.entity.EmployeesEntity;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.entity.dto.BaseQueryDto;
import com.example.mybatisplus.entity.dto.FilterQueryPageDto;
import com.example.mybatisplus.entity.vo.CityDeptCountVo;
import com.example.mybatisplus.entity.vo.EmpNameAndDeptNameVo;
import com.example.mybatisplus.entity.vo.EmpNameAndJobIdAndJobTitleVo;
import com.example.mybatisplus.entity.vo.EmpNameAndSalaryAndGradeLevelVo;
import com.example.mybatisplus.service.*;
import com.example.mybatisplus.vo.R;
import io.swagger.models.parameters.QueryParameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author lishizhan
 * @description:
 * @date 2022/8/25 13:43
 */
@RestController
@RequestMapping("select")
@RequiredArgsConstructor
public class SelectController {

    private final EmployeesService employeesService;
    private final DepartmentsService departmentsService;
    private final JobsService jobsService;
    private final LocationsService locationsService;
    private final UserService userService;
    private final UserMapper userMapper;

    private final EmployeesDao employeesDao;
    private final DepartmentsDao departmentsDao;
    private final JobsDao jobsDao;
    private final LocationsDao locationsDao;



    /**
     * 查询指定列名
     * 常用的QueryWrapper方法：
     * 1，select(String...sqlSelect) : 要查询的列名
     * 2，select(Class<T> entityClass,Predicate<TableFieldInfo> predicate): 过滤查询的字段（主键除外）
     */
    @GetMapping("selectColumn1")
    public R selectColumn1(){
        LambdaQueryWrapper<EmployeesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(EmployeesEntity::getEmployeeId,EmployeesEntity::getFirstName);
        List<EmployeesEntity> list = employeesService.getBaseMapper().selectList(queryWrapper);
        return R.ok(list);
    }
    @GetMapping("selectColumn2")
    public R selectColumn2(){
        LambdaQueryWrapper<EmployeesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(EmployeesEntity.class, tableFieldInfo -> {
            //是否有匹配的列名，有则返回
            return "first_name".equals(tableFieldInfo.getColumn());
        });
        List<EmployeesEntity> list = employeesService.getBaseMapper().selectList(queryWrapper);
        return R.ok(list);
    }
    @GetMapping("selectColumn3")
    public R selectColumn3(){
        LambdaQueryWrapper<EmployeesEntity> queryWrapper = new LambdaQueryWrapper<>(new EmployeesEntity());
        queryWrapper.select(tableFieldInfo -> {
            //不想查询某个列，注意查询需要加实体类，不然会报空指针
            return !"first_name".equals(tableFieldInfo.getColumn());
        });
        List<EmployeesEntity> list = employeesService.getBaseMapper().selectList(queryWrapper);
        return R.ok(list);
    }

    /**
     * UpdateWrapper 更新操作，比如字段值在某一个范围之内
     *  eq : =
     *  ne : !=
     *  gt : >
     *  lt : <
     *  ge : >=
     *  le : <=
     *  in : 包含
     *  isNull : 等于null
     *  between : [2,5]
     *  like : 模糊
     */
    @PostMapping("update1")
    public R update1(){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        /**
         * update user
         * set age=99
         * where id > 1;
         */
        updateWrapper.set(User::getAge,30);
        updateWrapper.gt(User::getId,2);
        boolean b = userService.update(null, updateWrapper);
        return R.ok(b);
    }

    /**
     * 自定义条件拼接
     */
    @GetMapping("myselfWrapper")
    public R myselfWrapper(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(User::getId,1);
        queryWrapper.eq(User::getAge,30);
        List<User> users = userMapper.selectMyWrapper(queryWrapper);
        return R.ok(users);
    }



    //------------------------------------分页查询语句------------------------------------------------------
    /**
     * 单表分页查询
     * @param query
     * @return
     */
    @GetMapping("getEmpPage")
    public R getEmpPage(FilterQueryPageDto query){
        //构造分页
        Page<EmployeesEntity> entityPage = new Page<>(query.getPage(),query.getSize());
        LambdaQueryWrapper<EmployeesEntity> queryWrapper = new LambdaQueryWrapper<>();
        //判断查询条件
        if (!StringUtils.isEmpty(query.getFilter())) queryWrapper.like(EmployeesEntity::getFirstName,query.getFilter());
        employeesService.page(entityPage,queryWrapper);
        return R.ok(employeesService.page(entityPage,queryWrapper));
    }

//----------------------------------连接查询------------------------------------------------------------

    /**
     * SQL92
     * 非等值连接：查询员工的姓名、工资、工资等级
     */
    @GetMapping("notEqPage")
    public R notEqPage(BaseQueryDto queryDto){
        /**
         * select e.last_name, e.salary, jg.grade_level
         * from employees e,
         *      job_grades jg
         * where e.salary between jg.grade_level and jg.highest_sal
         * order by jg.grade_level asc;
         */
        Page<EmpNameAndSalaryAndGradeLevelVo> page = new Page<>(queryDto.getPage(), queryDto.getSize());
        page =employeesDao.notEqPage(page);
        return R.ok(page);
    }
    /**
     * SQL92：等值连接
     *  查询员工名和对应的部门名
     */
    @GetMapping("eqPage1")
    public R eqPage1(BaseQueryDto queryDto){
        /**
         * # 查询员工名所对应的部门名
         * select e.last_name, d.department_name
         * from employees e,
         *      departments d
         * where e.department_id = d.department_id;
         */
        Page<EmpNameAndDeptNameVo> page = new Page<>(queryDto.getPage(), queryDto.getSize());
        page=employeesDao.epPage(page);
        return  R.ok(page);
    }
    /**
     * SQL92：等值连接
     *  查询员工名，工种号，工种名
     */
    @GetMapping("eqPage2")
    public R eqPage2(BaseQueryDto queryDto){
        /**
         * # 查询员工名，工种号，工种名
         * select e.last_name, j.job_id, j.job_title
         * from employees e,
         *      jobs j
         * where e.job_id = j.job_id;
         */
        Page<EmpNameAndJobIdAndJobTitleVo> page = new Page<>(queryDto.getPage(), queryDto.getSize());
        page=employeesDao.epPage2(page);
        return  R.ok(page);
    }

    /**
     * SQL92：等值连接
     *  查询有奖金的员工名和部门名
     */
    @GetMapping("eqPage3")
    public R eqPage3(BaseQueryDto queryDto){
        /**
         * # 查询有奖金的员工名和部门名
         * select e.last_name,d.department_name,e.commission_pct
         * from employees e,departments d
         * where e.department_id=d.department_id and e.commission_pct is not null ;
         */
        Page<EmpNameAndDeptNameVo> page = new Page<>(queryDto.getPage(), queryDto.getSize());
        page=employeesDao.epPage3(page);
        return  R.ok(page);
    }
    /**
     * SQL92：等值连接
     *  查询有奖金的员工名和部门名
     */
    @GetMapping("eqPage4")
    public R eqPage4(BaseQueryDto queryDto){
        /**
         * # 查询城市名中第二个字符为哦的部门名和城市名
         * select d.department_name, l.city
         * from locations l,
         *      departments d
         * where d.location_id = l.location_id
         *   and l.city like '_o%';
         */
        Page<EmpNameAndDeptNameVo> page = new Page<>(queryDto.getPage(), queryDto.getSize());
        //page=employeesDao.epPage4(page);
        return  R.ok(page);
    }
    /**
     * SQL 92,等值连接
     * 加入分组:
     *      查询每个城市的部门个数
     *      查询有奖金的每个部门的部门名和部门的领导编号和该部门的最低工资
     */
    @GetMapping("eqGroupBy")
    public R eqGroupBy(){
        /**查询每个城市的部门个数
         * select l.city as `city`, count(d.department_id) as `countDeptNum`
         * from locations l,
         *      departments d
         * where l.location_id = d.location_id
         * group by l.city
         */
        List<CityDeptCountVo> countVos = employeesDao.selectCityDeptCount();

        /**
         * 查询有奖金的每个部门的部门名和部门的领导编号和该部门的最低工资
         * select d.department_name, d.manager_id, MIN(e.salary)
         * from departments d,
         *      employees e
         * where d.department_id = e.department_id
         *   and e.commission_pct is not null
         * group by d.department_id
         */
        return R.ok(countVos);
    }

    /**
     * 总结：sql92语法的等值连接
     * 1，多表等值连接的结果为多表的交集部分
     * 2，n表连接至少需要n-1个连接条件
     * 3，多表的顺序没有要求
     * 4，一般需要为表起别名
     * 5，可以搭配所有的子句使用，如：排序、分组、筛选等
     * */










}
