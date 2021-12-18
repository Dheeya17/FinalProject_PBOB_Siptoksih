import Model.KeepModel;
import Model.ProductModel;
import Model.OrderModel;
import Model.EmployeeModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;




import Class.Admin;
import Class.Customer;

public class Main {
    static EmployeeModel employeeModel;
    static ProductModel productModel;
    static OrderModel orderModel;
    static KeepModel keepModel;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            employeeModel = new EmployeeModel();
            productModel = new ProductModel();
            orderModel = new OrderModel();
            keepModel = new KeepModel();

            System.out.println("Koneksi Berhasil");


            System.out.println("===== SELAMAT DATANG =====");
            System.out.println("1. Admin \n" +
                    "2. Guest \n" +
                    "Pilih menu login anda: ");
            String login = sc.nextLine();//Skip
            if (login.equals("1")) {
                ResultSet viewemployee = employeeModel.getEmployee();
                System.out.println("===== LOGIN ADMIN =====");
                System.out.print("Username: ");
                String username = input.readLine().trim();
                System.out.print("Password: ");
                String password = input.readLine().trim();
                String cek = "done";
                if (!viewemployee.wasNull()) {
                    while (viewemployee.next()) {
                        if (username.equals(viewemployee.getString("nama")) && (password.equals(viewemployee.getString("pass_word")))) {
                            Admin userLogin = new Admin(viewemployee.getString("nama"));
                            userLogin.greetUser();
                            while (true) {
                                System.out.flush();
                                userLogin.viewMenu();
                                System.out.print("Masukkan pilihan anda: ");
                                String option = sc.nextLine();
                                if (option.equals("7")) {
                                    break;
                                } else {
                                    switch (option) {
                                        case "1":
                                            createProduct();
                                            break;
                                        case "2":
                                            viewProducts();
                                            break;
                                        case "3":
                                            setProduct();
                                            break;
                                        case "4":
                                            productModel.delProduct();
                                            break;
                                        case "5":
                                        viewRiwayat();
                                            break;
                                        case "6":
                                            createOrder();
                                            break;
                                        default:
                                            System.out.println("Menu tidak tersedia");
                                            break;
                                    }
                                }
                            }
                        }
                        else{
                            cek = "failed";
                        }
                    }
                }
                else{
                    System.out.println("Data admin masih kosong");
                }
                if (cek.equals("failed")){
                    System.out.println("Gagal Login");
                }
            }
            else if (login.equals("2")) {
                System.out.print("Masukkan nama anda: ");
                String user = sc.nextLine();
                Customer userLogin = new Customer(user);
                userLogin.greetUser();
                while (true) {
                userLogin.viewMenu();
                    System.out.println("Masukkan pilihan anda: ");
                    String option = sc.nextLine();
                    if (option.equals("6")) {
                        break;
                    } else {
                        switch (option) {
                            case "1":
                                setKeep();
                                break;
                            case "2":
                                viewKeep();
                                break;
                            case "3":
                                keepModel.delKeep();
                                break;
                            case "4":
                                viewTotal();
                                break;
                            case "5":
                                viewProducts();
                                break;
                            default:
                                System.out.println("Menu tidak tersedia");
                        }
                    }

                }

            }





        } catch (SQLException | IOException e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());
        }
    }



        public static void viewProducts() throws SQLException {
            ResultSet viewproducts = productModel.getProduct();
            System.out.println("================ DAFTAR PRODUK SIPTOKSIH ================");
            while (viewproducts.next()) {
                System.out.printf("%-2s | %20s | %15s | %-7d %n", viewproducts.getInt("idProduct"),
                        viewproducts.getString("namabarang"),
                        viewproducts.getString("kategori"),
                        viewproducts.getInt("harga"));
                System.out.println("------------------------------------------------------------");
            }
        }

    public static void viewKeep() throws SQLException{
        ResultSet viewkeep = keepModel.getKeep();
        System.out.println("===== PESANAN ANDA =====");
        while (viewkeep.next()) {
            System.out.print(viewkeep.getString("namabarang"));
            System.out.print("    ");
            System.out.print(viewkeep.getInt("jumlah"));
            System.out.print("    ");
            System.out.println(viewkeep.getInt("subtotal"));

        }
    }

        public static void viewRiwayat() throws SQLException{
        ResultSet viewriwayat = orderModel.getRiwayat();
        while(viewriwayat.next()){
            System.out.print(viewriwayat.getInt("idOrder"));
            System.out.print("    ");
            System.out.print(viewriwayat.getString("tanggal"));
            System.out.print("    ");
            System.out.println(viewriwayat.getInt("total"));
        }
        }
        public static void createProduct() throws SQLException{
        Scanner sc = new Scanner(System.in);
            System.out.print("Nama Produk baru: ");
            String name = sc.nextLine();//Skip
            System.out.print("Kategori produk baru: ");
            String category = sc.nextLine();//Skip
            System.out.print("Harga produk baru: ");
            int price = sc.nextInt();

            int result = productModel.addProduct(name, category, price);
            if (result>0) {
                System.out.println("Berhasil update " + result + " baris");
            }
        }

            public static void setKeep() throws SQLException{
        try {
            System.out.print("Nomor pesanan list: ");
            int id = Integer.parseInt(input.readLine());
            System.out.print("Nomor barang yang dipilih: ");
            int nomer = Integer.parseInt(input.readLine());
            System.out.print("Jumlah barang: ");
            int jumlah = Integer.parseInt(input.readLine());
            int result = keepModel.addKeep(id, nomer, jumlah);
            if (result > 0) {
                System.out.println("Berhasil update " + result + " baris");
            }
        }
        catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void setProduct() throws SQLException {
            try {
                System.out.print("Nomor produk yang akan diubah: ");
                int id = Integer.parseInt(input.readLine()); //Skip
                System.out.print("Nama produk yang baru: ");
                String name = input.readLine().trim();//Skip
                System.out.print("Kategori produk yang baru: ");
                String category = input.readLine().trim(); //Skip
                System.out.print("Harga produk yang baru: ");
                int price = Integer.parseInt(input.readLine()); //Skip

                int result = productModel.upProduct(name, category, price, id);
                if (result > 0) {
                    System.out.println("Berhasil update " + result + " baris");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            public static void viewTotal () throws SQLException {
                ResultSet viewtotal = keepModel.getTotal();
                int count = 0;
                while (viewtotal.next()) {
                    count += viewtotal.getInt("subtotal");
                }
                System.out.println("Total pesanan adalah Rp " + count);
            }
    public static void createOrder() throws SQLException, IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = formatter.format(date);
        while(true) {
            System.out.print("ID Pesanan: ");
            int idOrder = Integer.parseInt(input.readLine());
            System.out.print("ID Pegawai: ");
            int idEmployee = Integer.parseInt(input.readLine());
            System.out.print("Nama Pelanggan: ");
            String Customer = input.readLine().trim();
            System.out.print("ID Produk: ");
            int idProduct = Integer.parseInt(input.readLine());
            System.out.print("Jumlah Produk: ");
            int jumlah = Integer.parseInt(input.readLine());


            int result = orderModel.addOrder(idOrder, idEmployee, tanggal, Customer, idProduct, jumlah);
            if (result > 0) {
                System.out.println("Berhasil update " + result + " baris");
                System.out.println("Apakah ingin menambah produk?[y/n]");
                String tambah = input.readLine().trim();
                if (tambah.equals("y")){
                    System.out.println("Tambah Produk lage");
                }
                else if(tambah.equals("n")){
                    System.out.println("================ NOTA TRANSAKSI ================");
                    System.out.println("Tanggal    |       Nama Barang     | Jumlah | Subtotal");
                    int count = 0;
                    ResultSet vieworder = orderModel.getOrder(idOrder);
                    while (vieworder.next()){
                        System.out.printf("%-10s | %20s | %2d | %-7d %n", vieworder.getString("tanggal"),
                                vieworder.getString("namabarang"),
                                vieworder.getInt("jumlah"),
                                vieworder.getInt("subtotal"));
                        count += vieworder.getInt("subtotal");
                    }
                    System.out.println("Total transaksi adalah Rp. " + count);
                    break;
                }
            }
        }
    }
}







