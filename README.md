# json3metode
Pemanfaatan Json untuk menampilkan data realtime covid-19 dengan Model View Presenter. 
Metode pemanggilan data json menggunakan Httphandler, retrofit dan volley.

Penggunaan 3 metode pemanggilan json bertujuan untuk membandingkan penggunaan memory dan cpu pada aplikasi. Selain itu dengan 3 motode ini kita bisa memilih metode mana yang cocok untuk aplikasi yang akan dibuat. Setiap metode memiliki kelebihan dan kelemahan sendiri, baik dari penggunaan baris code xml, baris code java maupun penggunaan memory dan cpu.

Format pemanggilan json:
1. Data Indonesia
   : Array - Object
2. Data berdasarkan provinsi
   : Array - Object - Object
3. Data Global
   : Object

Source code json3metode silahkan digunakan secara bebas untuk bahan ajar, latihan dan pengembangan aplikasi lainnya menggunakan metode pemanggilan data json

Untuk menggunakan SC ini silahkan atur first run activity di file AndroidManifest.xml
