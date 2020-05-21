package com.eelve.springbootpdf.service;

import com.eelve.springbootpdf.utils.*;

import org.springframework.stereotype.Service;

/**
 * @ClassName Html2PdfService
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/8 9:38
 * @Version 1.0
 **/
@Service
public class Html2PdfService {
    /**
     * windows执行文件
     */
    private String windowExePath;
    /**
     * linux执行文件
     */
    private String linuxExePath;

    /**
     * 解析生成PDF
     *
     * @param pageUrl
     * @return
     */
    public String excute(String pageUrl) throws Exception {
        String outputPath = new StringBuffer("/output/").append(BaseUtils.getDateStr("yyyyMMdd")).append("/pdf/").append(BaseUtils.uuid2()).append(".pdf").toString();
        String cmdStr = getCmdStr(pageUrl, outputPath);
        boolean success = CmdUtils.excute(cmdStr);
        if (success) {
            return outputPath;
        } else {
            if(FilesUtils.isExistNotCreate(outputPath)){
                return outputPath;
            }else {

                throw new Exception("转化异常！[" + outputPath + "]");
            }
        }
    }

    /**
     * 根据操作系统类别，获取不同的cmd字符串
     *
     * @param pageUrl
     * @param outputPath
     * @return
     */
    private String getCmdStr(String pageUrl, String outputPath) {
        StringBuilder cmdStr = new StringBuilder();
        String absoultOutputPath = PathUtils.getClassRootPath(outputPath);
        /************************输出文件夹检查************************/
        FilesUtils.checkFolderAndCreate(absoultOutputPath);
        String absoultExePath = "";
        if (OsInfo.isWindows()) {//windows系统
            absoultExePath = getWindowExePath();
            absoultOutputPath = PathUtils.getWindowsRightPath(absoultOutputPath);
        } else {//默认linux系统
            absoultExePath = getLinuxExePath();
            //需要给脚本授权
            //cmdStr.append("chmod +x ").append(absoultExePath).append(" && ");
            CmdUtils.excute("chmod +x " + absoultExePath);
        }
        cmdStr.append(absoultExePath).append(" --disable-smart-shrinking --load-error-handling ignore --header-center 资源使用月度运营报告  --header-line --header-spacing 3 --footer-spacing 3 --footer-center \"- 第 [page] 页-\" ").append(pageUrl).append(" ").append(absoultOutputPath);
        return cmdStr.toString();
    }

    public String getWindowExePath() {
        if (BaseUtils.isBlank(this.windowExePath)) {
            String absoultExePath = PathUtils.getClassRootPath("/plugin/window/wkhtmltopdf/bin/wkhtmltopdf");
            this.windowExePath = PathUtils.getWindowsRightPath(absoultExePath);
        }
        return this.windowExePath;
    }

    public void setWindowExePath(String windowExePath) {
        this.windowExePath = windowExePath;
    }

    public String getLinuxExePath() {
        if (BaseUtils.isBlank(this.linuxExePath)) {
            this.linuxExePath = PathUtils.getClassRootPath("/plugin/linux/wkhtmltox/bin/wkhtmltopdf");
        }
        return this.linuxExePath;
    }

    public void setLinuxExePath(String linuxExePath) {
        this.linuxExePath = linuxExePath;
    }
}
