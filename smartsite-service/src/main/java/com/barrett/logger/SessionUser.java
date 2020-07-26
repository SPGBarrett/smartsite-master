package com.barrett.logger;

/**
 * @program: smartsite
 * @description: Java bean for session user info
 * @author: Barrett
 * @create: 2020-02-13 10:39
 **/
public class SessionUser {
    public class Rootobject {
        public String code;
        public Data data;
        public int isEnd;
        public int nextStartIndex;
        public int count;
        public Map map;

        public Rootobject(String code, Data data, int isEnd, int nextStartIndex, int count, Map map) {
            this.code = code;
            this.data = data;
            this.isEnd = isEnd;
            this.nextStartIndex = nextStartIndex;
            this.count = count;
            this.map = map;
        }

        public Rootobject() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public int getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(int isEnd) {
            this.isEnd = isEnd;
        }

        public int getNextStartIndex() {
            return nextStartIndex;
        }

        public void setNextStartIndex(int nextStartIndex) {
            this.nextStartIndex = nextStartIndex;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }
    }

    public class Data {
        public int expires;
        public User user;

        public Data() {
        }

        public Data(int expires, User user) {
            this.expires = expires;
            this.user = user;
        }

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public class User {
        public String id;
        public boolean isNewRecord;
        public String createDate;
        public String updateDate;
        public Company company;
        public Office office;
        public String loginName;
        public String no;
        public String name;
        public String email;
        public String phone;
        public String mobile;
        public String userType;
        public String loginIp;
        public String loginDate;
        public String loginFlag;
        public String photo;
        public String oldLoginIp;
        public String oldLoginDate;
        public String roleNames;
        public boolean admin;
        public int roleType;

        public User(String id, boolean isNewRecord, String createDate, String updateDate, Company company, Office office, String loginName, String no, String name, String email, String phone, String mobile, String userType, String loginIp, String loginDate, String loginFlag, String photo, String oldLoginIp, String oldLoginDate, String roleNames, boolean admin, int roleType) {
            this.id = id;
            this.isNewRecord = isNewRecord;
            this.createDate = createDate;
            this.updateDate = updateDate;
            this.company = company;
            this.office = office;
            this.loginName = loginName;
            this.no = no;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.mobile = mobile;
            this.userType = userType;
            this.loginIp = loginIp;
            this.loginDate = loginDate;
            this.loginFlag = loginFlag;
            this.photo = photo;
            this.oldLoginIp = oldLoginIp;
            this.oldLoginDate = oldLoginDate;
            this.roleNames = roleNames;
            this.admin = admin;
            this.roleType = roleType;
        }

        public User() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Office getOffice() {
            return office;
        }

        public void setOffice(Office office) {
            this.office = office;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getOldLoginIp() {
            return oldLoginIp;
        }

        public void setOldLoginIp(String oldLoginIp) {
            this.oldLoginIp = oldLoginIp;
        }

        public String getOldLoginDate() {
            return oldLoginDate;
        }

        public void setOldLoginDate(String oldLoginDate) {
            this.oldLoginDate = oldLoginDate;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }
    }

    public class Company {
        public String id;
        public boolean isNewRecord;
        public String parentIds;
        public String name;
        public int sort;
        public int n_level;
        public Area area;
        public String type;
        public boolean isTreeLeaf;
        public String parentId;

        public Company(String id, boolean isNewRecord, String parentIds, String name, int sort, int n_level, Area area, String type, boolean isTreeLeaf, String parentId) {
            this.id = id;
            this.isNewRecord = isNewRecord;
            this.parentIds = parentIds;
            this.name = name;
            this.sort = sort;
            this.n_level = n_level;
            this.area = area;
            this.type = type;
            this.isTreeLeaf = isTreeLeaf;
            this.parentId = parentId;
        }

        public Company() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getN_level() {
            return n_level;
        }

        public void setN_level(int n_level) {
            this.n_level = n_level;
        }

        public Area getArea() {
            return area;
        }

        public void setArea(Area area) {
            this.area = area;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isTreeLeaf() {
            return isTreeLeaf;
        }

        public void setTreeLeaf(boolean treeLeaf) {
            isTreeLeaf = treeLeaf;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public class Area {
        public String id;
        public boolean isNewRecord;
        public String parentIds;
        public String name;
        public int sort;
        public int n_level;
        public boolean isTreeLeaf;
        public String parentId;

        public Area(String id, boolean isNewRecord, String parentIds, String name, int sort, int n_level, boolean isTreeLeaf, String parentId) {
            this.id = id;
            this.isNewRecord = isNewRecord;
            this.parentIds = parentIds;
            this.name = name;
            this.sort = sort;
            this.n_level = n_level;
            this.isTreeLeaf = isTreeLeaf;
            this.parentId = parentId;
        }

        public Area() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getN_level() {
            return n_level;
        }

        public void setN_level(int n_level) {
            this.n_level = n_level;
        }

        public boolean isTreeLeaf() {
            return isTreeLeaf;
        }

        public void setTreeLeaf(boolean treeLeaf) {
            isTreeLeaf = treeLeaf;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public class Office {
        public String id;
        public boolean isNewRecord;
        public String parentIds;
        public String name;
        public int sort;
        public int n_level;
        public Area1 area;
        public String type;
        public boolean isTreeLeaf;
        public String parentId;

        public Office(String id, boolean isNewRecord, String parentIds, String name, int sort, int n_level, Area1 area, String type, boolean isTreeLeaf, String parentId) {
            this.id = id;
            this.isNewRecord = isNewRecord;
            this.parentIds = parentIds;
            this.name = name;
            this.sort = sort;
            this.n_level = n_level;
            this.area = area;
            this.type = type;
            this.isTreeLeaf = isTreeLeaf;
            this.parentId = parentId;
        }

        public Office() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getN_level() {
            return n_level;
        }

        public void setN_level(int n_level) {
            this.n_level = n_level;
        }

        public Area1 getArea() {
            return area;
        }

        public void setArea(Area1 area) {
            this.area = area;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isTreeLeaf() {
            return isTreeLeaf;
        }

        public void setTreeLeaf(boolean treeLeaf) {
            isTreeLeaf = treeLeaf;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public class Area1 {
        public String id;
        public boolean isNewRecord;
        public String parentIds;
        public String name;
        public int sort;
        public int n_level;
        public boolean isTreeLeaf;
        public String parentId;

        public Area1(String id, boolean isNewRecord, String parentIds, String name, int sort, int n_level, boolean isTreeLeaf, String parentId) {
            this.id = id;
            this.isNewRecord = isNewRecord;
            this.parentIds = parentIds;
            this.name = name;
            this.sort = sort;
            this.n_level = n_level;
            this.isTreeLeaf = isTreeLeaf;
            this.parentId = parentId;
        }

        public Area1() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getN_level() {
            return n_level;
        }

        public void setN_level(int n_level) {
            this.n_level = n_level;
        }

        public boolean isTreeLeaf() {
            return isTreeLeaf;
        }

        public void setTreeLeaf(boolean treeLeaf) {
            isTreeLeaf = treeLeaf;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public class Map {
        public Map() {
        }
    }

    public SessionUser() {
    }
}
