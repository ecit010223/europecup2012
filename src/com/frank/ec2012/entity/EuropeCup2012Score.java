package com.frank.ec2012.entity;

import java.util.List;

public class EuropeCup2012Score {
	
	public static final Integer FIXED_GROUP_COUNT = 4;
	public static final Integer FIXED_TEAM_EVEN_GROUP_COUNT = 4;
	
	private List<Group> groups = null;

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public Group getGroup(int index) {
		
		return groups.get(index);
		
	}
	
	public Group getLastGroup() {
		
		if(groups.size() ==0)
			return groups.get(0);
		
		return groups.get(groups.size() - 1);
	}
	
	public Group getGroup(String groupName) {
		for(Group group : groups) {
			if(group.getName().equals(groupName)) {
				return group;
			}
		}
		
		return null;
	}
	
	
}
