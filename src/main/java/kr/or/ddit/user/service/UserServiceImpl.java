package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name="userDao")
	private UserDao userDao;
	
	public UserServiceImpl() {}
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}


	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<>();
		List<UserVo> userList = userDao.selectPagingUser(vo);
		map.put("pageVo", vo);
		map.put("userList", userList);
		map.put("pagination", (int)Math.ceil((double)userDao.selectAllUserCnt() /vo.getPageSize()));
		return map;
	}

	@Override
	public int modifyUser(UserVo vo) {
		return userDao.modifyUser(vo);
	}

	@Override
	public int countUser(String userid) {
		return userDao.countUser(userid);
	}

	@Override
	public int registUser(UserVo vo) {
		return userDao.registUser(vo);
	}

	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
