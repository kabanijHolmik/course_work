package com.coursework.models;

public class ChangeLog {
    private Long id;
    private String tableName;
    private Long recordID;
    private String changeType;

    public ChangeLog() {
    }

    public ChangeLog(Long id, String tableName, Long recordID, String changeType) {
        this.id = id;
        this.tableName = tableName;
        this.recordID = recordID;
        this.changeType = changeType;
    }

    public Long getId() {
        return this.id;
    }

    public String getTableName() {
        return this.tableName;
    }

    public Long getRecordID() {
        return this.recordID;
    }

    public String getChangeType() {
        return this.changeType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setRecordID(Long recordID) {
        this.recordID = recordID;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ChangeLog)) return false;
        final ChangeLog other = (ChangeLog) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$tableName = this.getTableName();
        final Object other$tableName = other.getTableName();
        if (this$tableName == null ? other$tableName != null : !this$tableName.equals(other$tableName)) return false;
        final Object this$recordID = this.getRecordID();
        final Object other$recordID = other.getRecordID();
        if (this$recordID == null ? other$recordID != null : !this$recordID.equals(other$recordID)) return false;
        final Object this$changeType = this.getChangeType();
        final Object other$changeType = other.getChangeType();
        if (this$changeType == null ? other$changeType != null : !this$changeType.equals(other$changeType))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ChangeLog;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $tableName = this.getTableName();
        result = result * PRIME + ($tableName == null ? 43 : $tableName.hashCode());
        final Object $recordID = this.getRecordID();
        result = result * PRIME + ($recordID == null ? 43 : $recordID.hashCode());
        final Object $changeType = this.getChangeType();
        result = result * PRIME + ($changeType == null ? 43 : $changeType.hashCode());
        return result;
    }

    public String toString() {
        return "ChangeLogEntry(id=" + this.getId() + ", tableName=" + this.getTableName() + ", recordID=" + this.getRecordID() + ", changeType=" + this.getChangeType() + ")";
    }
}
