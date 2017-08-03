package org.nr.tour.util;

import org.nr.tour.domain.Member;
import org.springframework.util.StringUtils;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public class MemberUtils {

    public static boolean isGuestMember(Member member) {
        return StringUtils.isEmpty(member.getMobilePhone());
    }

}
