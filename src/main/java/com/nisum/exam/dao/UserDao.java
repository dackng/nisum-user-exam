package com.nisum.exam.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nisum.exam.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao extends GenericDao<User> implements OperationsDao<User>{

	@Override
	public User save(User user) {
		this.saveEntity(user);
		return user;
	}
	
	@Override
    public Optional<User> findOne(String attributeId, String value) {
        return this.findOneEntity(attributeId, value, User.class);
    }
	
	@Override
    public List<User> findAll() {
        return this.findAllEntites(User.class);
    }
}
