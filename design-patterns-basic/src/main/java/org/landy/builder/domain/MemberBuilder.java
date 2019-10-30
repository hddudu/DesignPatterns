package org.landy.builder.domain;

/**
 * @author landyl
 * @create 5:05 PM 05/12/2018
 */
public class MemberBuilder {

    private String memberId;
    private String memberName;

    //私有构造器
    private MemberBuilder() {
    }

    //外部： 一个内部对象的构造器
    private MemberBuilder(Builder builder) {
        this.memberId = builder.memberId;
        this.memberName = builder.memberName;
    }

    //公共的内部构造器
    public static class Builder{
        private String memberId;
        private String memberName;

        public Builder() {
        }
        public Builder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }
        public Builder memberName(String memberName) {
            this.memberName = memberName;
            return this;
        }
        //提供给外部类构造对象: 以自己为参数
        public MemberBuilder build() {
            return new MemberBuilder(this);
        }
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return " 成员信息：" + memberId + "---" + memberName;
    }

}
