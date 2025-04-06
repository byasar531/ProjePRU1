import java.util.Scanner;

public class SinemaKayitSistemi {

    // Maksimum film ve müşteri sayısı
    static final int MAX_FILM = 10;
    static final int MAX_MUSTERI = 20;


    static String[] filmAdi = new String[MAX_FILM];
    static int[] filmSure = new int[MAX_FILM];
    static String[] filmTur = new String[MAX_FILM];


    static String[] musteriAd = new String[MAX_MUSTERI];
    static String[] musteriEmail = new String[MAX_MUSTERI];


    static String[][] biletler = new String[MAX_MUSTERI][MAX_FILM];

    // Mevcut film ve müşteri sayacı
    static int filmSayisi = 0;
    static int musteriSayisi = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int secim;

        do {
            System.out.println("\n--- Sinema Müşteri Kayıt Sistemi ---");
            System.out.println("1. Film Ekle");
            System.out.println("2. Müşteri Ekle");
            System.out.println("3. Bilet Kaydı Yap");
            System.out.println("4. Biletleri Listele");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    filmEkle(scanner);
                    break;
                case 2:
                    musteriEkle(scanner);
                    break;
                case 3:
                    biletKayit(scanner);
                    break;
                case 4:
                    biletListele();
                    break;
                case 5:
                    System.out.println("Çıkış yapılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim! Tekrar deneyin.");
            }
        } while (secim != 5);

        scanner.close();
    }


    static void filmEkle(Scanner scanner) {
        if (filmSayisi >= MAX_FILM) {
            System.out.println("Maksimum film sayısına ulaşıldı!");
            return;
        }

        System.out.print("Film adı: ");
        scanner.nextLine();
        String ad = scanner.nextLine();
        System.out.print("Film süresi (dakika): ");
        int sure = scanner.nextInt();
        System.out.print("Film türü: ");
        scanner.nextLine();
        String tur = scanner.nextLine();


        filmAdi[filmSayisi] = ad;
        filmSure[filmSayisi] = sure;
        filmTur[filmSayisi] = tur;

        filmSayisi++;
        System.out.println("Film başarıyla eklendi!");
    }


    static void musteriEkle(Scanner scanner) {
        if (musteriSayisi >= MAX_MUSTERI) {
            System.out.println("Maksimum müşteri sayısına ulaşıldı!");
            return;
        }

        System.out.print("Müşteri adı: ");
        scanner.nextLine();
        String ad = scanner.nextLine();
        System.out.print("Müşteri email: ");
        String email = scanner.nextLine();


        musteriAd[musteriSayisi] = ad;
        musteriEmail[musteriSayisi] = email;

        musteriSayisi++;
        System.out.println("Müşteri başarıyla eklendi!");
    }


    static void biletKayit(Scanner scanner) {
        if (musteriSayisi == 0 || filmSayisi == 0) {
            System.out.println("Kayıt yapılabilmesi için önce film ve müşteri eklenmelidir!");
            return;
        }

        System.out.println("--- Müşteri Bilgileri ---");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println((i + 1) + ". " + musteriAd[i] + " (" + musteriEmail[i] + ")");
        }

        System.out.print("Müşteri numarası seçin: ");
        int musteriNumarasi = scanner.nextInt();
        if (musteriNumarasi < 1 || musteriNumarasi > musteriSayisi) {
            System.out.println("Geçersiz müşteri numarası!");
            return;
        }

        System.out.println("--- Film Bilgileri ---");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println((i + 1) + ". " + filmAdi[i] + " (" + filmTur[i] + ", " + filmSure[i] + " dakika)");
        }

        System.out.print("Film numarası seçin: ");
        int filmNumarasi = scanner.nextInt();
        if (filmNumarasi < 1 || filmNumarasi > filmSayisi) {
            System.out.println("Geçersiz film numarası!");
            return;
        }


        biletler[musteriNumarasi - 1][filmNumarasi - 1] = filmAdi[filmNumarasi - 1];
        System.out.println("Bilet kaydı başarıyla yapıldı!");
    }


    static void biletListele() {
        System.out.println("--- Bilet Listesi ---");
        for (int i = 0; i < musteriSayisi; i++) {
            boolean biletVar = false;

            System.out.println(musteriAd[i] + " (" + musteriEmail[i] + "):");
            for (int j = 0; j < filmSayisi; j++) {
                if (biletler[i][j] != null) {
                    System.out.println("  - " + biletler[i][j]);
                    biletVar = true;
                }
            }

            if (!biletVar) {
                System.out.println("  Henüz bilet alınmamış.");
            }
        }
    }
}