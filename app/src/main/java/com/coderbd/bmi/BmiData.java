package com.coderbd.bmi;

import java.time.LocalDate;

public class BmiData {
    private int _id;
    private String _name;
    private String _status;
    private String _score;
    private String date;

    public BmiData() {
    }

    public BmiData(int _id) {
        this._id = _id;
    }

    public BmiData(int _id, String _name, String _status, String _score, String date) {
        this._id = _id;
        this._name = _name;
        this._status = _status;
        this._score = _score;
        this.date = date;
    }

    public BmiData(String _name, String _status, String _score, String date) {
        this._name = _name;
        this._status = _status;
        this._score = _score;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_status() {
        return _status;
    }

    public String get_score() {
        return _score;
    }

    public String getDate() {
        return date;
    }
}
