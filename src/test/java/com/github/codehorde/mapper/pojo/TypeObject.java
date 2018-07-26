package com.github.codehorde.mapper.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bao.mingfeng at 2018-07-18 15:58:03
 */
public class TypeObject<X extends Number, Y extends Serializable, Z > {

    private List<X> number;

    private List<Y  > seri;

    private List<Z> zzz;

    public List<X> getNumber() {
        return number;
    }

    public void setNumber(List<X> number) {
        this.number = number;
    }

    public List<Y> getSeri() {
        return seri;
    }

    public void setSeri(List<Y> seri) {
        this.seri = seri;
    }

    public List<Z> getZzz() {
        return zzz;
    }

    public void setZzz(List<Z> zzz) {
        this.zzz = zzz;
    }
}
