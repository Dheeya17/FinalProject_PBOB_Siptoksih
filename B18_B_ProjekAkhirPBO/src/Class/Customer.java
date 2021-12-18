package Class;

public class Customer extends Menu implements Greetings{
    public Customer(String nama){
        super(nama);
    }

    @Override
    public void greetUser() {
        System.out.println("Selamat datang pelanggan " + nama);
    }

    @Override
    public void viewMenu() {
        System.out.println("Menu: \n" +
                "1. Tambah Pesanan \n" +
                "2. Lihat Pesanan \n" +
                "3. Hapus Pesanan \n" +
                "4. Total Pesanan \n" +
                "5. Lihat Produk \n" +
                "6. Keluar \n");
    }
}
