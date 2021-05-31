package test;

import lib.annotations.MyColumn;
import lib.annotations.MyEntity;
import lib.annotations.MyId;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@MyEntity(tableName = "managers")
public class Manager {
    @MyId
    @MyColumn(columnName = "id")
    public Long id;

    @MyColumn(columnName = "name")
    public String name;

    @MyColumn(columnName = "surname")
    public String surname;
}
