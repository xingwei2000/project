package com.xw.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.xw.common.CodeMsg;
import com.xw.common.Constant;
import com.xw.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("file")
public class FileUploadController {


    /**
     * 上传文件接口
     * @param image
     * @return
     */
    @RequestMapping("uploadImg")
    public Object uploadImg(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
        // 获取文件的原始名
        String originalFilename = image.getOriginalFilename();
        // 获取文件扩展名
        String extName = FileUtil.extName(originalFilename);
        // 组装成新的文件
        String newFileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        newFileName = newFileName + "." + extName;
        // 获取upload文件上传路径
        String realPath = request.getServletContext().getRealPath(Constant.UPLOAD_FOLDER);
        // 此文件的物理路径
        String filePath = realPath + File.separator + newFileName;
        // URL路径
        String url = Constant.UPLOAD_FOLDER + "/" + newFileName;
        try {
            image.transferTo(new File(filePath));
            return new Result(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(CodeMsg.UPLOAD_IMG_ERROR) ;
    }
}
