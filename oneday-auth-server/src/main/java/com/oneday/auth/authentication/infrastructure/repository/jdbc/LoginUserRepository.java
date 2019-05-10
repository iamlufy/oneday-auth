package com.oneday.auth.authentication.infrastructure.repository.jdbc;

import com.oneday.BaseRepository;
import com.oneday.auth.authentication.infrastructure.dataobject.LoginUserDO;
import org.springframework.stereotype.Repository;

/**
 * @author zhuangzf
 * @date 2019/3/28 14:45
 */
@Repository
public interface LoginUserRepository extends BaseRepository<LoginUserDO> {
}
