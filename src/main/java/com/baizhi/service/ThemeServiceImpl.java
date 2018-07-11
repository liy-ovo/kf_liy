package com.baizhi.service;

import com.baizhi.dao.ThemeMapper;
import com.baizhi.entity.Theme;
import com.baizhi.entity.ThemeExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("themeService")
@Transactional
public class ThemeServiceImpl implements ThemeService{
    @Resource
    private ThemeMapper themeMapper;
    @Override
    public List<Theme> findAll() {
        return themeMapper.selectByExample(new ThemeExample());
    }

    @Override
    public List<Theme> findByTheme(ThemeExample example) {
        return themeMapper.selectByExample(example);
    }

    @Override
    public Theme findById(String id) {
        return themeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Theme theme) {
        theme.setId(UUID.randomUUID().toString());
        theme.setCreateTime(new Date());
        themeMapper.insertSelective(theme);
    }

    @Override
    public void update(Theme theme) {
        themeMapper.updateByPrimaryKeySelective(theme);
    }

    @Override
    public void delete(String id) {
        themeMapper.deleteByPrimaryKey(id);
    }
}
