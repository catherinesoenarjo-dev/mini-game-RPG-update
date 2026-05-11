
import java.util.Scanner;

public class arenaPertarungan {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        musuh[] gelombangMonster = new musuh[3];
        gelombangMonster[0] = new slime();
        gelombangMonster[1] = new naga();
        gelombangMonster[2] = new zombie();

        System.out.println("=======================================");
        System.out.println("ARENA RPG: GELOMBANG MONSTER");
        System.out.println("=======================================\n");
        System.out.println("AWAS! Sekelompok monster menghadap Anda!");

        boolean isBermain = true;

        while (isBermain) {
            System.out.println("\n--- STATUS MONSTER ---");
            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].healthPoint > 0) {
                    System.out.println((i + 1) + ". " + gelombangMonster[i].namaMusuh + "(HP: " + gelombangMonster[i].healthPoint + ")");
                } else {
                    System.out.println((i + 1) + ". " + gelombangMonster[i].namaMusuh + " [TEWAS]");
                }
            }
            System.out.println("4. Kabur dari pertarungan");
            System.out.println("\nPilih target monster yang ingin diserang (1/2/3) atau 4 untuk kabur: ");
            int pilihanTarget = input.nextInt();

            if (pilihanTarget == 4) {
                System.out.println("Anda lari terbirit-birit dari arena...");
                isBermain = false;
                continue;
            }

            if (pilihanTarget < 1 || pilihanTarget > 3) {
                System.out.println("Pilihan tidak valid! Anda membuang giliran.");
            } else {
                System.out.println("Masukkan kekuatan serangan Anda (10-100): ");
                int power = input.nextInt();

                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                int indeksMonster = pilihanTarget - 1;
                gelombangMonster[indeksMonster].terimaDamage(power);
            }
            System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");
            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].healthPoint > 0) {
                    musuh monsterAktif = gelombangMonster[i];
                    monsterAktif.suaraKhas();

                    if (monsterAktif instanceof bisaTerbang) {
                        System.out.println("[PERINGATAN! SERANGAN UDARA TERDETEKSI]");
                        bisaTerbang monsterTerbang = (bisaTerbang) monsterAktif;
                        monsterTerbang.lepasLandas();
                        monsterTerbang.seranganUdara();
                    } else {
                        monsterAktif.serangPemain();
                    }
                } else {
                    System.out.println(gelombangMonster[i].namaMusuh + " sudah kalah dan tidak bisa menyerang.");
                    if (gelombangMonster[i] instanceof bisaLoot) {
                        bisaLoot monsterLoot = (bisaLoot) gelombangMonster[i];
                        monsterLoot.jatuhkanItem();
                    }
                }
            }
            System.out.println("-----------------------------------------------------------------------------");
            boolean semuaMati = true;
            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].healthPoint > 0) {
                    semuaMati = false;
                    break;
                }
            }
            if (semuaMati) {
                System.out.println("\nSELAMAT! Anda telah menyapu bersih gelombang monster ini.");
                isBermain = false;
            }
        }
        input.close();
            System.out.println("Permainan Berakhir.");
    }
}
