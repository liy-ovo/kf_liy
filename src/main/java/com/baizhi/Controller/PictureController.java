package com.baizhi.Controller;

import com.baizhi.entity.Picture;
import com.baizhi.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/picture")
@ResponseBody
public class PictureController {
    @Resource
    private PictureService pictureService;
    @RequestMapping("/upload")
    public List<String> upload(String goodsId, MultipartFile[] pics, HttpServletRequest request){
        List<String> paths = new ArrayList<String>();
        for (MultipartFile pic : pics) {
            String realPath = request.getServletContext().getRealPath("/back/goods/img/"+goodsId);
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                pic.transferTo(new File(realPath, pic.getOriginalFilename()));
            }catch (Exception e){
                e.printStackTrace();
            }
            String path = realPath.replace('\\', '/').split("webapp")[1]+"/"+pic.getOriginalFilename();
            paths.add(path);
            Picture picture = new Picture();
            picture.setName(pic.getOriginalFilename());
            picture.setGoodsId(goodsId);
            picture.setPath(path);
            pictureService.add(picture);
        }
        return paths;
    }
    @RequestMapping("/queryByGoodsId")
    public List<Picture> queryByGoodsId(String id){
        return pictureService.findByGoodsId(id);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        pictureService.delete(id);
    }
}
