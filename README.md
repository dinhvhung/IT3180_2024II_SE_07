# IT3180_2024II_SE_07
Đây là bài tập lớn môn Nhập môn Công nghệ phần mềm của nhóm SE_07, thành viên gồm: Đinh Việt Hùng, Bùi Đình Sang, Đỗ Văn Tài
# Hướng dẫn sử dụng
- Tải code về
- Vào application.properties cóp mấy dòng sau:<br>

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver<br>
spring.datasource.url=jdbc:mysql://mysql-apartmentapp-apartment-app.d.aivencloud.com:26373/defaultdb?ssl-mode=REQUIRED<br>
spring.datasource.username=...<br>
#<br>
spring.datasource.password=...<br>
spring.jpa.properties.hibernate.jdbc.time_zone=UTC+1<br>
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect<br>
spring.jpa.properties.hibernate.id.new_generator_mappings = false<br>
spring.jpa.properties.hibernate.show_sql = false<br>
spring.jpa.properties.hibernate.format_sql = false<br>
spring.datasource.hikari.connectionTimeout=30000<br>
spring.datasource.initialize=true<br>
spring.jpa.hibernate.ddl-auto=update<br>
spring.datasource.hikari.idleTimeout=600000<br>
spring.datasource.hikari.maxLifetime=1800000<br>
spring.datasource.hikari.maximumPoolSize=200<br>
<br>
server.port=8080<br>
<br>
spring.mail.host=smtp.gmail.com<br>
spring.mail.port=587<br>
spring.mail.username=dvhung090705@gmail.com<br>
spring.mail.password=...<br>
spring.mail.properties.mail.smtp.auth=true<br>
spring.mail.properties.mail.smtp.starttls.enable=true<br>
- Ib trưởng nhóm để lấy username + pass của database<br> và pass của mail
