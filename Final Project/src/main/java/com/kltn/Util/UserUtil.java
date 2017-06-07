package com.kltn.Util;

import com.kltn.entities.User;

import java.util.List;

/**
 * Created by TinNguyen on 6/7/17.
 */

public interface UserUtil {
    List<User> findAllActiveUserExpectAdmin();
}
