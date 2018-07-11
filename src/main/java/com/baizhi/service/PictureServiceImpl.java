package com.baizhi.service;

import com.baizhi.dao.PictureMapper;
import com.baizhi.entity.Picture;
import com.baizhi.entity.PictureExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureMapper pictureMapper;
    @Override
    public List<Picture> findByGoodsId(String id) {
        PictureExample example = new PictureExample();
        example.createCriteria().andGoodsIdEqualTo(id);
        List<Picture> pictures = pictureMapper.selectByExample(example);
        return pictures;
    }

    @Override
    public Picture findById(String id) {
        return pictureMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Picture picture) {
        picture.setId(UUID.randomUUID().toString());
        picture.setCreateTime(new Date());
        pictureMapper.insertSelective(picture);
    }

    @Override
    public void update(Picture picture) {

    }

    @Override
    public void delete(String id) {
        Picture picture = pictureMapper.selectByPrimaryKey(id);
        if(picture!=null){
            if(picture.getPath()!=null){
                File file = new File(picture.getPath());
                file.delete();
                if (file.exists()){
                    throw new RuntimeException("----------没删掉------------");
                }
            }
            pictureMapper.deleteByPrimaryKey(id);
        }
    }
}
