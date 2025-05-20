package com.fx.login.service;

import com.fx.login.model.User;
import com.fx.login.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private ServiceUtil serviceUtil;

    // Helper method để lấy UserRepo (giả sử bạn đã có)
    private UserRepo getUserRepo() {
        return serviceUtil.getUserRepo();
    }


    public User save(User entity) {
        return serviceUtil.getUserRepo().save(entity);
    }

    public User update(User entity) {
        return serviceUtil.getUserRepo().save(entity);
    }

    public void delete(User entity) {
        serviceUtil.getUserRepo().delete(entity);
    }

    public void delete(Long id) {
        serviceUtil.getUserRepo().deleteById(id);
    }

    public Optional<User> find(Long id) {
        return serviceUtil.getUserRepo().findById(id);
    }

    public List<User> findAll() {
        return serviceUtil.getUserRepo().findAll();
    }



    public List<User> findResidentsByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList(); // Trả về danh sách rỗng nếu input không hợp lệ
        }

        // Cách 1: Nếu UserRepo của bạn có method hỗ trợ query trực tiếp (Khuyến khích)
        // Giả sử UserRepo có: List<User> findAllByIdInAndRole(List<Long> ids, User.Role role);
        // Thì bạn chỉ cần:
        // return getUserRepo().findAllByIdInAndRole(ids, User.Role.Resident);

        // Cách 2: Nếu UserRepo không có method trên, bạn phải lấy tất cả User theo ID, sau đó lọc.
        // Cách này kém hiệu quả hơn nếu danh sách ID lớn.
        List<User> usersFound = getUserRepo().findAllById(ids); // Lấy tất cả user có ID trong danh sách
        // Lọc ra những user có vai trò là Resident
        return usersFound.stream()
                .filter(user -> user.getRole() == User.Role.Resident)
                .collect(Collectors.toList());
    }
    // --- KẾT THÚC PHẦN SỬA ---

    public boolean authenticate(String username, String password){
        Optional<User> user = this.findByEmail(username);
        if(user.isEmpty()){
            return false;
        }else{
            User u = user.get();
            if(password.equals(u.getPassword())) return true;
            else return false;
        }
    }

    public Optional<User> findByEmail(String email) {
        return getUserRepo().findByEmail(email);
    }

    public void deleteInBatch(List<User> users) {
        // Giả sử JpaRepository có deleteAllInBatch hoặc deleteInBatch
        getUserRepo().deleteAllInBatch(users); // Hoặc deleteInBatch(users)
    }
    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    // Optional: Thêm method này nếu bạn cần lấy tất cả Resident mà không theo ID
    public List<User> findAllResidents() {
        // Giả sử UserRepo có: List<User> findByRole(User.Role role);
        // return getUserRepo().findByRole(User.Role.Resident);

        // Hoặc lọc thủ công nếu UserRepo không có findByRole:
        return findAll().stream()
                .filter(user -> user.getRole() == User.Role.Resident)
                .collect(Collectors.toList());
    }

    /**
     * Đếm số lượng cư dân trong hệ thống
     * @return Số lượng cư dân
     */
    public long getResidentCount() {
        // Cách 1: Nếu UserRepo có method hỗ trợ đếm trực tiếp
        // return getUserRepo().countByRole(User.Role.Resident);

        // Cách 2: Sử dụng stream để đếm
        return findAll().stream()
                .filter(user -> user.getRole() == User.Role.Resident)
                .count();
    }
}
