package Class;

public class Admin extends Menu implements Greetings{
    public Admin (String nama){
        super(nama);
    }

    @Override
    public void greetUser() {
        System.out.println("Selamat datang admin " + nama );
    }

    @Override
    public void viewMenu() {
        System.out.println("Menu: \n" +
                "1. Tambah Produk \n" +
                "2. Lihat Produk \n" +
                "3. Ubah Produk \n" +
                "4. Hapus Produk \n" +
                "5. Riwayat Transaksi \n" +
                "6. Tambah transaksi \n" +
                "7. Keluar \n");
    }
}
