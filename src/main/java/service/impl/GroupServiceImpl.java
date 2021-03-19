package service.impl;

import dao.GroupDao;
import model.Group;
import service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    private GroupDao groupDAO;

    public GroupServiceImpl(GroupDao groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDAO.getAllGroupInfo();
    }

    @Override
    public boolean saveGroup(Group group) {
        return groupDAO.saveGroup(group);
    }

    @Override
    public Group getGroupInfoById(Long groupId) {
        return groupDAO.getGroupInfoById(groupId);
    }

    @Override
    public boolean updateGroup(Group group) {
        return groupDAO.updateGroup(group);
    }

    @Override
    public boolean softDeleteGroup(Long id) {
        return groupDAO.softDeleteGroupById(id);
    }
}
