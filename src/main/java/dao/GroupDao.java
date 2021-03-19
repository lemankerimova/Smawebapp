package dao;

import model.Group;

import java.util.List;

public interface GroupDao {
    List<Group> getAllGroupInfo();

    boolean saveGroup(Group group);

    Group getGroupInfoById(Long groupId);

    boolean updateGroup(Group group);

    boolean softDeleteGroupById(Long id);
}
