# IT3180_2024II_SE_07
Đây là bài tập lớn môn Nhập môn Công nghệ phần mềm của nhóm SE_07, thành viên gồm: Đinh Việt Hùng, Bùi Đình Sang, Đỗ Văn Tài
# Hướng dẫn sử dụng
- Tải code về
- Vào application.properties cóp mấy dòng sau:<br>

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver<br>
spring.datasource.url=jdbc:mysql://mysql-apartmentapp-apartment-app.d.aivencloud.com:26373/defaultdb?ssl-mode=REQUIRED
spring.datasource.username=avnadmin
#
spring.datasource.password=AVNS_g280i3DXgtpvw9t6MDC
spring.jpa.properties.hibernate.jdbc.time_zone=UTC+1
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
spring.jpa.properties.hibernate.id.new_generator_mappings = false
spring.jpa.properties.hibernate.show_sql = false
spring.jpa.properties.hibernate.format_sql = false
spring.datasource.hikari.connectionTimeout=30000 
spring.datasource.initialize=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.hikari.idleTimeout=600000 
spring.datasource.hikari.maxLifetime=1800000
spring.datasource.hikari.maximumPoolSize=200
- Đổi lại username và pass trong application.properties cho giống với mySQL của mình
