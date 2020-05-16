package com.example.fjob.Api.entity;

public class JobAll {


    /**
     * errno : 200
     * errmsg : 成功
     * data : {"id":52,"jobName":"类军","cpnAddress":null,"good1":"e34f943f-ad02-4f2a-905a-752bb5ff6b9c小米","good2":"17781140502","good3":null,"good4":null,"jobPay":"250","conditionOne":"1","conditionTwo":"2","condition3":"本科","workContent":null,"workContentShow":"代码","workAddress":null,"cpnImage":"caa7ccd7-bb18-4a78-af2e-51b820975159timg.jpeg","cpnName1":"小米","dizhi":"武汉"}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 52
         * jobName : 类军
         * cpnAddress : null
         * good1 : e34f943f-ad02-4f2a-905a-752bb5ff6b9c小米
         * good2 : 17781140502
         * good3 : null
         * good4 : null
         * jobPay : 250
         * conditionOne : 1
         * conditionTwo : 2
         * condition3 : 本科
         * workContent : null
         * workContentShow : 代码
         * workAddress : null
         * cpnImage : caa7ccd7-bb18-4a78-af2e-51b820975159timg.jpeg
         * cpnName1 : 小米
         * dizhi : 武汉
         */

        private int id;
        private String jobName;
        private Object cpnAddress;
        private String good1;
        private String good2;
        private Object good3;
        private Object good4;
        private String jobPay;
        private String conditionOne;
        private String conditionTwo;
        private String condition3;
        private Object workContent;
        private String workContentShow;
        private Object workAddress;
        private String cpnImage;
        private String cpnName1;
        private String dizhi;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public Object getCpnAddress() {
            return cpnAddress;
        }

        public void setCpnAddress(Object cpnAddress) {
            this.cpnAddress = cpnAddress;
        }

        public String getGood1() {
            return good1;
        }

        public void setGood1(String good1) {
            this.good1 = good1;
        }

        public String getGood2() {
            return good2;
        }

        public void setGood2(String good2) {
            this.good2 = good2;
        }

        public Object getGood3() {
            return good3;
        }

        public void setGood3(Object good3) {
            this.good3 = good3;
        }

        public Object getGood4() {
            return good4;
        }

        public void setGood4(Object good4) {
            this.good4 = good4;
        }

        public String getJobPay() {
            return jobPay;
        }

        public void setJobPay(String jobPay) {
            this.jobPay = jobPay;
        }

        public String getConditionOne() {
            return conditionOne;
        }

        public void setConditionOne(String conditionOne) {
            this.conditionOne = conditionOne;
        }

        public String getConditionTwo() {
            return conditionTwo;
        }

        public void setConditionTwo(String conditionTwo) {
            this.conditionTwo = conditionTwo;
        }

        public String getCondition3() {
            return condition3;
        }

        public void setCondition3(String condition3) {
            this.condition3 = condition3;
        }

        public Object getWorkContent() {
            return workContent;
        }

        public void setWorkContent(Object workContent) {
            this.workContent = workContent;
        }

        public String getWorkContentShow() {
            return workContentShow;
        }

        public void setWorkContentShow(String workContentShow) {
            this.workContentShow = workContentShow;
        }

        public Object getWorkAddress() {
            return workAddress;
        }

        public void setWorkAddress(Object workAddress) {
            this.workAddress = workAddress;
        }

        public String getCpnImage() {
            return cpnImage;
        }

        public void setCpnImage(String cpnImage) {
            this.cpnImage = cpnImage;
        }

        public String getCpnName1() {
            return cpnName1;
        }

        public void setCpnName1(String cpnName1) {
            this.cpnName1 = cpnName1;
        }

        public String getDizhi() {
            return dizhi;
        }

        public void setDizhi(String dizhi) {
            this.dizhi = dizhi;
        }
    }
}
