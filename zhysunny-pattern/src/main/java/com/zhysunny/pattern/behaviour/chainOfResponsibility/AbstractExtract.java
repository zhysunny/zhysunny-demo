package com.zhysunny.pattern.behaviour.chainOfResponsibility;

/**
 * 接口的抽象类
 * @author 章云
 * @date 2019/5/28 21:36
 */
public abstract class AbstractExtract implements Extract {

    private AbstractExtract nextExtract;

    public AbstractExtract getNextExtract() {
        return nextExtract;
    }

    public void setNextExtract(AbstractExtract nextExtract) {
        this.nextExtract = nextExtract;
    }

    public void nextExtract() {
        this.extract();
        if (this.getNextExtract() != null) {
            this.getNextExtract().nextExtract();
        }
    }

}
