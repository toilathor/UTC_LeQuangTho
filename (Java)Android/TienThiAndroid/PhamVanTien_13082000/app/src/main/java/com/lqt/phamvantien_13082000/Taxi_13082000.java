package com.lqt.phamvantien_13082000;
//Bài 1 ảnh 1
public class Taxi_13082000 implements Comparable<Taxi_13082000>{
    private int Id;
    private String SoXe;
    private double QuangDuong;
    private int DonGia;
    private int KhuyenMai;
    public Taxi_13082000(int id, String soXe, double quangDuong, int donGia, int khuyenMai) {
        Id = id;
        SoXe = soXe;
        QuangDuong = quangDuong;
        DonGia = donGia;
        KhuyenMai = khuyenMai;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getSoXe() {
        return SoXe;
    }
    public void setSoXe(String soXe) {
        SoXe = soXe;
    }
    public double getQuangDuong() {
        return QuangDuong;
    }
    public void setQuangDuong(double quangDuong) {
        QuangDuong = quangDuong;
    }
    public int getDonGia() {
        return DonGia;
    }
    public void setDonGia(int donGia) {
        DonGia = donGia;
    }
    public int getKhuyenMai() {
        return KhuyenMai;
    }
    public void setKhuyenMai(int khuyenMai) {
        KhuyenMai = khuyenMai;
    }
    @Override
    public int compareTo(Taxi_13082000 o) {
        return (int) ((o.DonGia * o.QuangDuong * (100 - o.KhuyenMai)/100) - (this.DonGia * this.QuangDuong * (100 - this.KhuyenMai)/100));
    }
}
