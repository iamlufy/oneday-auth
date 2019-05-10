package com.oneday.auth.authentication.infrastructure.tunnel;

import com.oneday.BaseRepository;
import com.oneday.BaseServiceImpl;
import com.oneday.auth.authentication.infrastructure.dataobject.LoginUserDO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author zhuangzf
 * @date 2019/3/28 14:46
 */
@Service
public class LoginUserJdbcTunnelImpl extends BaseServiceImpl<LoginUserDO> implements LoginUserTunnel {
    public LoginUserJdbcTunnelImpl(BaseRepository<LoginUserDO> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Optional<LoginUserDO> findByUsername(String username) {
        return findOptionalOne(((root, criteriaQuery, criteriaBuilder) ->
                criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username)).getRestriction()));
    }
}
