package com.eelve.springbootpdf.controller;

import com.eelve.springbootpdf.service.Html2PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    Html2PdfService html2PdfService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/view")
    public ModelAndView echart(){
        ModelAndView mav=new ModelAndView();
        int data[] = {10,15, 30, 45, 55, 60, 62, 80,80,62, 60, 55, 45, 30, 15, 10};
        List list = new ArrayList<>(Arrays.asList(data));
        int data2[] ={8,5, 25, 30, 35, 55, 62, 78,65,55, 60, 45, 42, 15, 12, 5};
        mav.setViewName("view");
        mav.addObject("data1",data);
        mav.addObject("data2",data2);
        return mav;
    }

    @RequestMapping("/html2pdf/{pageUrl}")
    public String html2pdf(@PathVariable(value="pageUrl") String pageUrl) {
        try {
            pageUrl ="http://localhost:8080/" + pageUrl;
            String fileRelativePath = html2PdfService.excute(pageUrl);
            return "redirect:" + fileRelativePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "/error";
        }
    }

    @RequestMapping(value = "/preview")
    public void dashboardView(HttpServletRequest request, HttpServletResponse response, String id){
        log.info("文件id为：" + id);
        //验证参数，然年就可用查询出真实的文件地址
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
        //验证参数，然年就可用查询出真实的文件地址
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
