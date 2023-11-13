package christmas.io;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInput implements Input{

    @Override
    public String readLine() {
        String str = Console.readLine().trim();
        validate(str);
        return str;
    }

    private void validate(String str) {

        if(str == null) {
            throw new IllegalArgumentException("[ERROR] 값을 입력 하셔야 합니다.");
        }

        if(str.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 값을 입력하셔야 합니다.");
        }
    }



}
