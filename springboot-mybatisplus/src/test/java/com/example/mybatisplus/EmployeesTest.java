package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.mybatisplus.dao.EmployeesDao;
import com.example.mybatisplus.entity.EmployeesEntity;
import com.example.mybatisplus.vo.DeptAvgVo;
import com.example.mybatisplus.vo.EmpAndDeptVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootTest
@Slf4j
public class EmployeesTest {
    @Autowired
    private EmployeesDao employeesDao;

    /*
     * 查询没有金且工资低于18000
     * */
    @Test
    public void Test() {
        List<EmployeesEntity> selectList = employeesDao.selectList(new LambdaQueryWrapper<EmployeesEntity>().isNotNull(EmployeesEntity::getCommissionPct));
        selectList.forEach(employeesEntity -> System.out.println("employeesEntity = " + employeesEntity));
    }

    /*
     * 工资总和
     * */
    @Test
    public void salaryTest() {
        Double salaryAll = employeesDao.getSalarySum();
        log.info("[EmployeesTest] [salaryTest] 工资总和：{}", salaryAll);
    }

    /*
     * 最高工资
     * */
    @Test
    public void maxSalaryTest() {
        Double salaryMax = employeesDao.getSalaryMax();
        log.info("[EmployeesTest] [salaryTest] 工资总和：{}", salaryMax);
    }

    /*
     * 查询每个部门的平均工资
     * */
    @Test
    public void deptAndSalaryAvgTest() {
        List<DeptAvgVo> deptAvgVos = employeesDao.deptAndSalaryAvg();
        log.info("[EmployeesTest] [deptAndSalaryAvgTest] 每个部门的工资列表：{}", deptAvgVos);
    }
}
