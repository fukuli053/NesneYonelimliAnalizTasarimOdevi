package com.furkanergun ;

import java.sql.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            /***** Bağlantı kurulumu *****/
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nyat",
                    "postgres", "123456");
            if (conn == null)
                System.out.println("Bağlantı girişimi başarısız!");

            MerkeziIslemBirimi islemBirimi;
            Scanner klavye = new Scanner(System.in);

            while(true){
                System.out.println("Kullanıcı Adınızı Giriniz: ");
                String kullaniciAdi = klavye.nextLine();

                String sql = "SELECT *  FROM \"users\" WHERE username='" + kullaniciAdi + "'";
                /***** Sorgu çalıştırma *****/
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                /***** Bağlantı sonlandırma *****/
                conn.close();

                if (rs.next() == false){
                    System.out.println("Böyle bir kullanıcı adı bulunamadı.");
                }else{
                    System.out.println("Şifre Giriniz:");
                    String sifre = klavye.nextLine();
                    String kullaniciSifre = rs.getString("password");
                    if (sifre.equals(kullaniciSifre)){
                        System.out.println("Giriş başarılı.");
                        //Merkezi işlem birimi oluşturuluyor.
                        islemBirimi = MerkeziIslemBirimi.getInstance(new Kullanici(kullaniciAdi, sifre));
                        break;
                    }
                    System.out.println("Giriş işlemi başarısız.");
                }
            }

            while (true){
                System.out.println("----------İşlemler----------");
                System.out.println("1-Sıcaklık Oku");
                System.out.println("2-Soğutucu Aç");
                System.out.println("3-Soğutucu Kapat");
                System.out.println("4-Çıkış");

                int menu = klavye.nextInt();
                switch (menu){
                    case 1:
                        //Ortam sıcaklığı yazdırılıyor.
                        System.out.println("Ortam Sıcaklığı: " + islemBirimi.sicaklikGonder());
                        break;
                    case 2:
                        //Soğutucu açılıyor.
                        islemBirimi.sogutucuAc();
                        break;
                    case 3:
                        //Soğutucu kapatılıyor.
                        islemBirimi.sogutucuKapat();
                        break;
                }
                if (menu == 4) break;
            }

        } catch (SQLException ex) {
            //Hata mevcutsa yazdırılıyor.
            System.out.println("error - "+ex.getMessage());
        }
    }
}
