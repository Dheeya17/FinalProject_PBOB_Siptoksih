CREATE TABLE customer (
    idCustomer serial PRIMARY KEY not null,
    nama VARCHAR (100) NOT NULL
);

Create table orderdetail (
	idOrder serial not null,
	idEmployee serial not null,
	tanggal date not null,
	customer varchar (20) not null,
	idProduct serial not null,
	jumlah int not null
);
Create table product (
	idProduct serial PRIMARY KEY not null,
	namabarang varchar (20) not null,
	kategori varchar (20) not null,
	harga int not null
);

Create table employee(
	idEmployee serial PRIMARY KEY not null,
	nama varchar (30) not null,
	alamat varchar (50) not null,
	nohp varchar (13) not null,
	status varchar (10) not null,
	gaji varchar (10) not null,
	pass_word varchar (10) not null);

Create table keep (
	idKeep serial not null,
	idProduct serial not null,
	jumlah integer not null
	);
	
ALTER TABLE orderdetail
ADD FOREIGN KEY (idProduct) REFERENCES product (idProduct)
ON DELETE CASCADE ON UPDATE CASCADE,
ADD FOREIGN KEY (idEmployee) REFERENCES employee (idEmployee)
ON DELETE CASCADE ON UPDATE CASCADE;



insert into employee (nama, alamat, nohp, status, gaji, pass_word)
values ('Rara', 'Jember', '085465498532', 'Aktif', '2000000', 'raraLojin');
insert into product (namabarang, kategori, harga)
values ('Bolpoin Snowman', 'Alat Tulis', 1500);
insert into product (namabarang, kategori, harga)
values ('Buku Tulis Garis', 'Alat Tulis', 3000);
insert into product (namabarang, kategori, harga)
values ('Tip-x Kenko', 'Alat Tulis', 5000);
insert into product (namabarang, kategori, harga)
values ('Gula', 'Bahan Pokok', 12000);
insert into product (namabarang, kategori, harga)
values ('Minyak', 'Bahan Pokok', 29000);
insert into product (namabarang, kategori, harga)
values ('Beras', 'Bahan Pokok', 112000);

insert into orderdetail (idOrder, idEmployee, tanggal, customer, idProduct, jumlah)
values (1, 1, '29 August 2021', 'Fery', 1, 5);


create view detailorder as
select orderdetail.idOrder, employee.nama, orderdetail.tanggal, orderdetail.customer, product.namabarang, orderdetail.jumlah, (orderdetail.jumlah * product.harga) as subtotal
from orderdetail
join employee on orderdetail.idEmployee = employee.idEmployee
join product on orderdetail.idProduct = product.idProduct;


CREATE view ordertotal as select orderdetail.idOrder, orderdetail.tanggal, Sum(detailorder.subtotal) AS total
FROM orderdetail
INNER JOIN detailorder ON orderdetail.idOrder = detailorder.idOrder
GROUP BY orderdetail.idOrder, orderdetail.tanggal;


ALTER TABLE keep
ADD FOREIGN KEY (idProduct) REFERENCES product (idProduct)
ON DELETE CASCADE ON UPDATE CASCADE;

create view detailkeep as
select keep.idKeep, product.namabarang, keep.jumlah, (keep.jumlah * product.harga) as subtotal
from keep
join product on keep.idProduct = product.idProduct;


CREATE view keeptotal as select keep.idKeep, Sum(detailkeep.subtotal) AS total
FROM keep
INNER JOIN detailkeep ON keep.idKeep = detailkeep.idKeep
GROUP BY keep.idKeep;
