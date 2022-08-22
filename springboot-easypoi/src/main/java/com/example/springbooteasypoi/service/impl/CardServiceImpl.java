package com.example.springbooteasypoi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbooteasypoi.entity.Card;
import com.example.springbooteasypoi.mapper.CardMapper;
import com.example.springbooteasypoi.service.CardService;
import org.springframework.stereotype.Service;

/**
 * (Card)表服务实现类
 *
 * @author makejava
 * @since 2022-08-21 23:44:02
 */
@Service("cardService")
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

}
