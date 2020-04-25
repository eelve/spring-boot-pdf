package com.eelve.springbootpdf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName PdfContrroller
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/4/24 21:01
 * @Version 1.0
 **/
@Controller
@Slf4j
public class PdfContrroller {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/preview")
    public void dashboardView(HttpServletRequest request, HttpServletResponse response, String id){
        log.info("文件id为：" + id);
        File file = null;
        try{
            switch (id){
                case "1" :
                    file = ResourceUtils.getFile("classpath:泰山版Java开发手册.pdf");
                    break;
                case "2" :
                    String filePath = getClass().getResource("/").getPath()+"eelve.pdf";
                    file = ResourceUtils.getFile(filePath);
                    break;
                default:
                    file = ResourceUtils.getFile("classpath:eelve.pdf");
                    break;
            }

            if (file.exists()) {
                byte[] data = null;
                FileInputStream input=null;

                input= new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
            }
        }catch (Exception e){
            log.info( "Exception"+e);
        }
    }

    /**
     * 使用PathVariable取值
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "/view/{id}")
    public void view(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") String id){
        log.info("文件id为：" + id);
        File file = null;
        try{
            switch (id){
                case "1" :
                    file = ResourceUtils.getFile("classpath:泰山版Java开发手册.pdf");
                    break;
                case "2" :
                    String filePath = getClass().getResource("/").getPath()+"eelve.pdf";
                    file = ResourceUtils.getFile(filePath);
                    break;
                default:
                    file = ResourceUtils.getFile("classpath:eelve.pdf");
                    break;
            }

            if (file.exists()) {
                byte[] data = null;
                FileInputStream input=null;

                input= new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
            }
        }catch (Exception e){
            log.info( "Exception"+e);
        }
    }
}
