package comt.example.administrator.OurPersionTV;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class OldManExaminationData {


    private List<OldManExaminationDataBean> OldManExaminationData;

    public List<OldManExaminationDataBean> getOldManExaminationData() {
        return OldManExaminationData;
    }

    public void setOldManExaminationData(List<OldManExaminationDataBean> OldManExaminationData) {
        this.OldManExaminationData = OldManExaminationData;
    }

    public static class OldManExaminationDataBean {
        /**
         * TastePreference : {"id":"0444355d-83f8-4a73-8600-22c01f1762b2","mark":"0","remark":null,"createrId":"eef70e20-c0a3-44ee-bbb9-5e671b21bdd7","createrName":"超级管理员","createdTime":"2017-08-08T11:26:13.687","modifierId":"eef70e20-c0a3-44ee-bbb9-5e671b21bdd7","modifierName":"超级管理员","modifiedTime":"2017-08-08T11:26:13.687","pid":"35dcff87-0602-42ac-bad3-a29b0afa59d4","dictionaryType":"口味偏好","dictionaryName":"软","sysDataState":"1","costData":0,"SimpleCode":null}
         * BathTypes : {"id":"afec004e-daa0-4a60-bfb1-17eba8ecc2a2","mark":"0","remark":"坐着由护工帮忙洗的","createrId":"eef70e20-c0a3-44ee-bbb9-5e671b21bdd7","createrName":"admin","createdTime":"2017-09-26T11:01:46.193","modifierId":"eef70e20-c0a3-44ee-bbb9-5e671b21bdd7","modifierName":"admin","modifiedTime":"2017-09-26T11:01:46.193","pid":"d29da308-71fd-4ec8-9e9a-0196d81d918f","dictionaryType":"入浴种类","dictionaryName":"座","sysDataState":"1","costData":0,"SimpleCode":null}
         * Id : c89406c0-ff1e-46e2-889a-a696e84293fc
         * TestUserId : 00000000-0000-0000-0000-000000000000
         * TestUserName : 测试
         * TestTime : 2017-10-16T15:27:41
         * DiastolicPressure : 2
         * SystolicPressure : 20
         * Pulse : 20
         * Temperature : 2
         * Breathing : 2
         * MeasuresAndEffect : null
         * CreateId : eef70e20-c0a3-44ee-bbb9-5e671b21bdd7
         * CreaterName : admin
         * CreaterTime : 2017-10-16T15:27:28.567
         * ModifiedId : eef70e20-c0a3-44ee-bbb9-5e671b21bdd7
         * ModifiedName : admin
         * ModifiedTime : 2017-10-16T15:27:28.567
         * IsDelete : false
         * Remark : null
         * OldPersonName : 张三峰
         * OldPersonId : dfbffcbd-b22c-44b4-b0cb-3303390a5227
         * HeartRate : 2
         * Weight : 2
         * MainFoodIntake : 2
         * FoodIntake : 2
         * DefecationVolume : 2
         * DefecationFrequency : 2
         * UrinaryOutput : 0
         * MicturitionTimes : 0
         * BathType : afec004e-daa0-4a60-bfb1-17eba8ecc2a2
         * BathReason : null
         * DietaryPreferenceId : 0444355d-83f8-4a73-8600-22c01f1762b2
         */

        private TastePreferenceBean TastePreference;
        private BathTypesBean BathTypes;
        private String Id;
        private String TestUserId;
        private String TestUserName;
        private String TestTime;
        private float DiastolicPressure;
        private float SystolicPressure;
        private float Pulse;
        private float Temperature;
        private float Breathing;
        private Object MeasuresAndEffect;
        private String CreateId;
        private String CreaterName;
        private String CreaterTime;
        private String ModifiedId;
        private String ModifiedName;
        private String ModifiedTime;
        private boolean IsDelete;
        private Object Remark;
        private String OldPersonName;
        private String OldPersonId;
        private float HeartRate;
        private float Weight;
        private float MainFoodIntake;
        private float FoodIntake;
        private float DefecationVolume;
        private float DefecationFrequency;
        private float UrinaryOutput;
        private float MicturitionTimes;
        private String BathType;
        private Object BathReason;
        private String DietaryPreferenceId;

        public TastePreferenceBean getTastePreference() {
            return TastePreference;
        }

        public void setTastePreference(TastePreferenceBean TastePreference) {
            this.TastePreference = TastePreference;
        }

        public BathTypesBean getBathTypes() {
            return BathTypes;
        }

        public void setBathTypes(BathTypesBean BathTypes) {
            this.BathTypes = BathTypes;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getTestUserId() {
            return TestUserId;
        }

        public void setTestUserId(String TestUserId) {
            this.TestUserId = TestUserId;
        }

        public String getTestUserName() {
            return TestUserName;
        }

        public void setTestUserName(String TestUserName) {
            this.TestUserName = TestUserName;
        }

        public String getTestTime() {
            return TestTime;
        }

        public void setTestTime(String TestTime) {
            this.TestTime = TestTime;
        }

        public float getDiastolicPressure() {
            return DiastolicPressure;
        }

        public void setDiastolicPressure(float DiastolicPressure) {
            this.DiastolicPressure = DiastolicPressure;
        }

        public float getSystolicPressure() {
            return SystolicPressure;
        }

        public void setSystolicPressure(float SystolicPressure) {
            this.SystolicPressure = SystolicPressure;
        }

        public float getPulse() {
            return Pulse;
        }

        public void setPulse(float Pulse) {
            this.Pulse = Pulse;
        }

        public float getTemperature() {
            return Temperature;
        }

        public void setTemperature(float Temperature) {
            this.Temperature = Temperature;
        }

        public float getBreathing() {
            return Breathing;
        }

        public void setBreathing(float Breathing) {
            this.Breathing = Breathing;
        }

        public Object getMeasuresAndEffect() {
            return MeasuresAndEffect;
        }

        public void setMeasuresAndEffect(Object MeasuresAndEffect) {
            this.MeasuresAndEffect = MeasuresAndEffect;
        }

        public String getCreateId() {
            return CreateId;
        }

        public void setCreateId(String CreateId) {
            this.CreateId = CreateId;
        }

        public String getCreaterName() {
            return CreaterName;
        }

        public void setCreaterName(String CreaterName) {
            this.CreaterName = CreaterName;
        }

        public String getCreaterTime() {
            return CreaterTime;
        }

        public void setCreaterTime(String CreaterTime) {
            this.CreaterTime = CreaterTime;
        }

        public String getModifiedId() {
            return ModifiedId;
        }

        public void setModifiedId(String ModifiedId) {
            this.ModifiedId = ModifiedId;
        }

        public String getModifiedName() {
            return ModifiedName;
        }

        public void setModifiedName(String ModifiedName) {
            this.ModifiedName = ModifiedName;
        }

        public String getModifiedTime() {
            return ModifiedTime;
        }

        public void setModifiedTime(String ModifiedTime) {
            this.ModifiedTime = ModifiedTime;
        }

        public boolean isIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(boolean IsDelete) {
            this.IsDelete = IsDelete;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public String getOldPersonName() {
            return OldPersonName;
        }

        public void setOldPersonName(String OldPersonName) {
            this.OldPersonName = OldPersonName;
        }

        public String getOldPersonId() {
            return OldPersonId;
        }

        public void setOldPersonId(String OldPersonId) {
            this.OldPersonId = OldPersonId;
        }

        public float getHeartRate() {
            return HeartRate;
        }

        public void setHeartRate(float HeartRate) {
            this.HeartRate = HeartRate;
        }

        public float getWeight() {
            return Weight;
        }

        public void setWeight(float Weight) {
            this.Weight = Weight;
        }

        public float getMainFoodIntake() {
            return MainFoodIntake;
        }

        public void setMainFoodIntake(float MainFoodIntake) {
            this.MainFoodIntake = MainFoodIntake;
        }

        public float getFoodIntake() {
            return FoodIntake;
        }

        public void setFoodIntake(float FoodIntake) {
            this.FoodIntake = FoodIntake;
        }

        public float getDefecationVolume() {
            return DefecationVolume;
        }

        public void setDefecationVolume(float DefecationVolume) {
            this.DefecationVolume = DefecationVolume;
        }

        public float getDefecationFrequency() {
            return DefecationFrequency;
        }

        public void setDefecationFrequency(float DefecationFrequency) {
            this.DefecationFrequency = DefecationFrequency;
        }

        public float getUrinaryOutput() {
            return UrinaryOutput;
        }

        public void setUrinaryOutput(float UrinaryOutput) {
            this.UrinaryOutput = UrinaryOutput;
        }

        public float getMicturitionTimes() {
            return MicturitionTimes;
        }

        public void setMicturitionTimes(float MicturitionTimes) {
            this.MicturitionTimes = MicturitionTimes;
        }

        public String getBathType() {
            return BathType;
        }

        public void setBathType(String BathType) {
            this.BathType = BathType;
        }

        public Object getBathReason() {
            return BathReason;
        }

        public void setBathReason(Object BathReason) {
            this.BathReason = BathReason;
        }

        public String getDietaryPreferenceId() {
            return DietaryPreferenceId;
        }

        public void setDietaryPreferenceId(String DietaryPreferenceId) {
            this.DietaryPreferenceId = DietaryPreferenceId;
        }

        public static class TastePreferenceBean {
            /**
             * id : 0444355d-83f8-4a73-8600-22c01f1762b2
             * mark : 0
             * remark : null
             * createrId : eef70e20-c0a3-44ee-bbb9-5e671b21bdd7
             * createrName : 超级管理员
             * createdTime : 2017-08-08T11:26:13.687
             * modifierId : eef70e20-c0a3-44ee-bbb9-5e671b21bdd7
             * modifierName : 超级管理员
             * modifiedTime : 2017-08-08T11:26:13.687
             * pid : 35dcff87-0602-42ac-bad3-a29b0afa59d4
             * dictionaryType : 口味偏好
             * dictionaryName : 软
             * sysDataState : 1
             * costData : 0
             * SimpleCode : null
             */

            private String id;
            private String mark;
            private Object remark;
            private String createrId;
            private String createrName;
            private String createdTime;
            private String modifierId;
            private String modifierName;
            private String modifiedTime;
            private String pid;
            private String dictionaryType;
            private String dictionaryName;
            private String sysDataState;
            private int costData;
            private Object SimpleCode;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getCreaterId() {
                return createrId;
            }

            public void setCreaterId(String createrId) {
                this.createrId = createrId;
            }

            public String getCreaterName() {
                return createrName;
            }

            public void setCreaterName(String createrName) {
                this.createrName = createrName;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public String getModifierId() {
                return modifierId;
            }

            public void setModifierId(String modifierId) {
                this.modifierId = modifierId;
            }

            public String getModifierName() {
                return modifierName;
            }

            public void setModifierName(String modifierName) {
                this.modifierName = modifierName;
            }

            public String getModifiedTime() {
                return modifiedTime;
            }

            public void setModifiedTime(String modifiedTime) {
                this.modifiedTime = modifiedTime;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getDictionaryType() {
                return dictionaryType;
            }

            public void setDictionaryType(String dictionaryType) {
                this.dictionaryType = dictionaryType;
            }

            public String getDictionaryName() {
                return dictionaryName;
            }

            public void setDictionaryName(String dictionaryName) {
                this.dictionaryName = dictionaryName;
            }

            public String getSysDataState() {
                return sysDataState;
            }

            public void setSysDataState(String sysDataState) {
                this.sysDataState = sysDataState;
            }

            public int getCostData() {
                return costData;
            }

            public void setCostData(int costData) {
                this.costData = costData;
            }

            public Object getSimpleCode() {
                return SimpleCode;
            }

            public void setSimpleCode(Object SimpleCode) {
                this.SimpleCode = SimpleCode;
            }
        }

        public static class BathTypesBean {
            /**
             * id : afec004e-daa0-4a60-bfb1-17eba8ecc2a2
             * mark : 0
             * remark : 坐着由护工帮忙洗的
             * createrId : eef70e20-c0a3-44ee-bbb9-5e671b21bdd7
             * createrName : admin
             * createdTime : 2017-09-26T11:01:46.193
             * modifierId : eef70e20-c0a3-44ee-bbb9-5e671b21bdd7
             * modifierName : admin
             * modifiedTime : 2017-09-26T11:01:46.193
             * pid : d29da308-71fd-4ec8-9e9a-0196d81d918f
             * dictionaryType : 入浴种类
             * dictionaryName : 座
             * sysDataState : 1
             * costData : 0
             * SimpleCode : null
             */

            private String id;
            private String mark;
            private String remark;
            private String createrId;
            private String createrName;
            private String createdTime;
            private String modifierId;
            private String modifierName;
            private String modifiedTime;
            private String pid;
            private String dictionaryType;
            private String dictionaryName;
            private String sysDataState;
            private int costData;
            private Object SimpleCode;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCreaterId() {
                return createrId;
            }

            public void setCreaterId(String createrId) {
                this.createrId = createrId;
            }

            public String getCreaterName() {
                return createrName;
            }

            public void setCreaterName(String createrName) {
                this.createrName = createrName;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public String getModifierId() {
                return modifierId;
            }

            public void setModifierId(String modifierId) {
                this.modifierId = modifierId;
            }

            public String getModifierName() {
                return modifierName;
            }

            public void setModifierName(String modifierName) {
                this.modifierName = modifierName;
            }

            public String getModifiedTime() {
                return modifiedTime;
            }

            public void setModifiedTime(String modifiedTime) {
                this.modifiedTime = modifiedTime;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getDictionaryType() {
                return dictionaryType;
            }

            public void setDictionaryType(String dictionaryType) {
                this.dictionaryType = dictionaryType;
            }

            public String getDictionaryName() {
                return dictionaryName;
            }

            public void setDictionaryName(String dictionaryName) {
                this.dictionaryName = dictionaryName;
            }

            public String getSysDataState() {
                return sysDataState;
            }

            public void setSysDataState(String sysDataState) {
                this.sysDataState = sysDataState;
            }

            public int getCostData() {
                return costData;
            }

            public void setCostData(int costData) {
                this.costData = costData;
            }

            public Object getSimpleCode() {
                return SimpleCode;
            }

            public void setSimpleCode(Object SimpleCode) {
                this.SimpleCode = SimpleCode;
            }
        }
    }
}
