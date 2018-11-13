package by.future.servicebiz.test.excel.demo;

import by.future.common.exception.ByException;
import by.future.common.exception.CodeEnum;
import by.future.common.utils.ExportFileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * demo
 *
 * @author by@Deng
 * @create 2017-10-21 15:41
 */
public class Demo {

    /**
     * 订单报表导出
     * @author by@Deng
     * @date 2017/10/21 上午11:04
     */
    public void getTicketOrderForm(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");

        //创建excel
        HSSFWorkbook wb = new HSSFWorkbook();

        //创建字体一
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeight((short) 200);

        //创建样式一
        HSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setFont(font);   //字体
        titleStyle.setWrapText(true);

        //创建sheet
        HSSFSheet sheet = wb.createSheet("订单报表");
        sheet.setDefaultColumnWidth(25);

        int rowNum = 0;
        int colNum = 0;    //列增长

        //创建第一行
        HSSFRow row = sheet.createRow(rowNum);

        List<String> firstRowList = new ArrayList<>();
        firstRowList.add("序号");
        firstRowList.add("产品名称");
        firstRowList.add("产品编号");
        firstRowList.add("使用日期");
        firstRowList.add("订单总金额(单位:元)");
        firstRowList.add("来源渠道");
        firstRowList.add("报名数量(单位:人)");
        firstRowList.add("订单金额(单位:元)");
        firstRowList.add("订单状态");
        firstRowList.add("订单类型");
        firstRowList.add("财务备注");
        firstRowList.add("供应商名称");
        firstRowList.add("供应商电话");
        firstRowList.add("分销商名称");
        firstRowList.add("分销商电话");
        firstRowList.add("员工工号");
        firstRowList.add("员工姓名");
        firstRowList.add("员工电话");
        firstRowList.add("分销人员");
        firstRowList.add("联系人姓名");
        firstRowList.add("联系人电话");
        firstRowList.add("集合信息");

        for(String text :firstRowList){
            HSSFCell cell = row.createCell(colNum++);  //创建列单元格
            cell.setCellValue(text);
            cell.setCellStyle(titleStyle);  //样式
        }

        String[] orderIds = orderId.split(",");

        for (String orderIdElem : orderIds) {

            //获取相应订单详细信息
            /*VorderEntity vorderEntity = orderBaseService.getOrderDetailById(userId,orderIdElem);
            if (vorderEntity == null) throw new ZealException("该"+orderIdElem+"订单存在问题,导出失败");

            List<String> dataList = new ArrayList<>();  //将数据放入集合中
            dataList.add(String.valueOf(++rowNum));   //序号
            dataList.add(vorderEntity.getProName());    //产品名称
            dataList.add(vorderEntity.getProNum());    //产品编号
            dataList.add(DateUtils.formatDate(vorderEntity.getLeaveDate(), "yyyy-MM-dd"));    //使用日期
            dataList.add(String.valueOf(vorderEntity.getTotalAmount()));    //订单总金额

            // 来源渠道  0系统订单 1自拟订单 2合作电商订单
            String orderType = "";
            if (StringUtils.equals(vorderEntity.getType(),"0")) {
                orderType = "系统分销订单";
            } else if (StringUtils.equals(vorderEntity.getType(),"1")) {
                orderType = "自拟订单";
            } else if (StringUtils.equals(vorderEntity.getType(),"2")) {
                orderType = "合作电商订单";
            }
            dataList.add(orderType);    //来源渠道

            dataList.add(String.valueOf(vorderEntity.getTrueVisitorCount()));    //报名数量
            dataList.add(String.valueOf(vorderEntity.getTotalAmount()));    //订单金额

            //订单状态
            String statu = StringUtils.equals(vorderEntity.getPayStatu(),"0")?"未结款":"已结款";
            dataList.add(statu);    //订单状态
            dataList.add(orderType);    //订单类型
            dataList.add(vorderEntity.getFinanceRemark());    //财务备注
            dataList.add(vorderEntity.getSupName());    //供应商名称
            dataList.add(vorderEntity.getSupPhoneNum());    //供应商电话
            dataList.add(vorderEntity.getDisName());    //分销商名称
            dataList.add(vorderEntity.getDisPhoneNum());    //分销商电话
            dataList.add(vorderEntity.getEmployeeNum());    //员工号
            dataList.add(vorderEntity.getEmployeeName());    //员工名称
            dataList.add(vorderEntity.getEmployeePhoneNum());    //员工联系电话
            dataList.add(vorderEntity.getSupSaleEmployeeId());   //分销人员
            dataList.add(vorderEntity.getLinkName());   //联系人姓名
            dataList.add(vorderEntity.getLinkPhone());   //联系人电话
            dataList.add(vorderEntity.getTicketGather());   //集合信息

            int col=0;
            row = sheet.createRow(rowNum);
            for(String text :dataList){
                HSSFCell cell = row.createCell(col++);  //创建列单元格
                cell.setCellValue(text);
                cell.setCellStyle(titleStyle);  //样式
            }*/
        }

        String name = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");//修改时间格式
        try {
            String fileName = "订单报表" + name + ".xls";
            ExportFileUtils.setAttachmentFileName(response, fileName);
            OutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            wb.write(out);
            out.flush();
            out.close();
        }catch(Exception e) {
            throw new ByException(CodeEnum.UNKNOW_ERROR.getCode(), CodeEnum.UNKNOW_ERROR.getMessage());
        }
    }

}
