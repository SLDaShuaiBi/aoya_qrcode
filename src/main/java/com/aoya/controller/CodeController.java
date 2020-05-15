package com.aoya.controller;

import com.aoya.domain.AyCode;
import com.aoya.mapper.AyCodeMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:aoya_qrcode
 * @Author:SL
 * @Date:2020-5-11 21:57
 */
@RestController
@EnableTransactionManagement
public class CodeController {
    @Autowired
    AyCodeMapper ayCodeMapper;

    @PostMapping("/code/add/{name}")
    @Transactional
    public Map<String, String> add(@PathVariable("name") String name, MultipartFile file, HttpServletRequest request) {
        Map<String, String> ret = new HashMap<String, String>();
        String imgSavePath = "E:/images/upload/";
        if (file == null) {
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            ret.put("type", "error");
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        AyCode ayCode = new AyCode();
        ayCode.setAycName(name);
        ayCode.setAycUpdateTime(new Date());
        int i = ayCodeMapper.insertSelective(ayCode);
        Integer id = ayCode.getAycId();
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }
        //获取项目根目录加上图片目录 webapp/static/imgages/upload/
        String orgImgSavePath = imgSavePath + id;
        File savePathFile = new File(orgImgSavePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        String filename = new Date().getTime() + "." + suffix;
        orgImgSavePath = orgImgSavePath + "/" + filename;
        String codeImgSavePath = imgSavePath + id + "/" + "code" + filename;
        try {
            //将文件保存指定目录
            file.transferTo(new File(orgImgSavePath));
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ayCode.setAycImgUrl(orgImgSavePath);
        ayCode.setAycCodeUrl(codeImgSavePath);
        ayCodeMapper.updateByPrimaryKeySelective(ayCode);
        if (this.orCode("http://192.168.8.23:8888/code.html?id=" + id, codeImgSavePath)) {
            System.out.println("ok,成功");
        } else {
            System.out.println("no,失败");
        }
        ret.put("type", "success");
        ret.put("msg", "上传图片成功！");
        ret.put("filepath", orgImgSavePath);
        ret.put("filename", filename);
        return ret;
    }

    @GetMapping("/view")
    public ModelAndView view() {
        return new ModelAndView("code");
    }

    private boolean orCode(String content, String path) {
        /*
         * 图片的宽度和高度
         */
        int width = 300;
        int height = 300;
        // 图片的格式
        String format = "png";

        // 定义二维码的参数
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 定义字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 纠错的等级 L > M > Q > H 纠错的能力越高可存储的越少，一般使用M
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置图片边距
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            // 最终生成 参数列表 （1.内容 2.格式 3.宽度 4.高度 5.二维码参数）
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 写入到本地
            Path file = new File(path).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        File savePathFile = new File("F:\\aoya");
        if (!savePathFile.exists()) {
            savePathFile.mkdir();
            System.out.println("创建文件夹");
        } else {
            System.out.println("baibai");
        }

    }
}
