package by.future.servicebiz.monitor.entity;



import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Author：by@Deng
 * @Date：2018/6/29 9:43
 */
public class MarketingMonitorEntity {

    // 主键
    private Integer id;

    // DB或者Queue
    private String monitorType;

    // 监控名称
    private String monitorName;

    // 描述
    private String monitorDesc;

    // 监控sql
    private String monitorSql;

    // 轮询间隔时间(分钟)
    private Integer rollingMinute;

    // 运算符
    private String operator;

    // 邮箱预警记录数
    private Integer emailAlarmCount;

    // 短信预警记录数
    private Integer mobileAlarmCount;

    // 单位：%或者条等
    private String alarmCountUnit;

    // 预警消息
    private String alarmMessage;

    // 接收邮箱
    private String alarmEmail;

    // 接收手机
    private String alarmMobile;

    // 开始监控日期
    private Date monitorStartDate;

    // 结束监控日期
    private Date monitorEndDate;

    // 开始监控时间HH:mm:ss
    private String monitorStartTime;

    // 结束监控时间HH:mm:ss
    private String monitorEndTime;

    // 全限定类名
    private String className;

    // 方法名
    private String methodName;

    // 最后执行时间
    private Timestamp lastExecuteTime;

    // 创建时间
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getMonitorDesc() {
        return monitorDesc;
    }

    public void setMonitorDesc(String monitorDesc) {
        this.monitorDesc = monitorDesc;
    }

    public String getMonitorSql() {
        return monitorSql;
    }

    public void setMonitorSql(String monitorSql) {
        this.monitorSql = monitorSql;
    }

    public Integer getRollingMinute() {
        return rollingMinute;
    }

    public void setRollingMinute(Integer rollingMinute) {
        this.rollingMinute = rollingMinute;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getEmailAlarmCount() {
        return emailAlarmCount;
    }

    public void setEmailAlarmCount(Integer emailAlarmCount) {
        this.emailAlarmCount = emailAlarmCount;
    }

    public Integer getMobileAlarmCount() {
        return mobileAlarmCount;
    }

    public void setMobileAlarmCount(Integer mobileAlarmCount) {
        this.mobileAlarmCount = mobileAlarmCount;
    }

    public String getAlarmCountUnit() {
        return alarmCountUnit;
    }

    public void setAlarmCountUnit(String alarmCountUnit) {
        this.alarmCountUnit = alarmCountUnit;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public String getAlarmEmail() {
        return alarmEmail;
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
    }

    public String getAlarmMobile() {
        return alarmMobile;
    }

    public void setAlarmMobile(String alarmMobile) {
        this.alarmMobile = alarmMobile;
    }

    public Date getMonitorStartDate() {
        return monitorStartDate;
    }

    public void setMonitorStartDate(Date monitorStartDate) {
        this.monitorStartDate = monitorStartDate;
    }

    public Date getMonitorEndDate() {
        return monitorEndDate;
    }

    public void setMonitorEndDate(Date monitorEndDate) {
        this.monitorEndDate = monitorEndDate;
    }

    public String getMonitorStartTime() {
        return monitorStartTime;
    }

    public void setMonitorStartTime(String monitorStartTime) {
        this.monitorStartTime = monitorStartTime;
    }

    public String getMonitorEndTime() {
        return monitorEndTime;
    }

    public void setMonitorEndTime(String monitorEndTime) {
        this.monitorEndTime = monitorEndTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Timestamp getLastExecuteTime() {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Timestamp lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
