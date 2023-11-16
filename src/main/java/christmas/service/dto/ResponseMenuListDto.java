package christmas.service.dto;

import java.util.List;
import java.util.Map;

public class ResponseMenuListDto {

    private Map<String, List<ResponseMenuDto>> menuLists;

    public ResponseMenuListDto(Map<String, List<ResponseMenuDto>> menuLists) {
        this.menuLists = menuLists;
    }

    public Map<String, List<ResponseMenuDto>> getMenuLists() {
        return menuLists;
    }
}
