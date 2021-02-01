package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	
	List<UserVo> selectAllUser();
	UserVo selectUser(String userid);
	Map<String, Object> selectPagingUser(PageVo vo);
	int modifyUser(UserVo vo);
	int countUser(String userid);
	int registUser(UserVo vo);
	int deleteUser(String userid);
}
