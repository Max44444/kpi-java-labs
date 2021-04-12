package com.company.view.dictionaries;

public enum Dictionary {
    GREETING("Hello from java lab work!\n"),
    FAREWELL("Good bye...\n"),
    FILENAME("filename"),
    NOT_FOUND("Sorry, we couldn't find any tour...\n"),
    ENTER("Please enter"),
    FOUND_TOURS("Found Tours: \n"),
    ERROR_COMMAND("Error parse command...\n"),
    SAVE_QUESTION("Save? (1 - Yes / 0 - No)"),
    ID("id"),
    NAME("name"),
    TOUR_OPERATOR("tour operator"),
    VISIT_POINTS("visit points"),
    PRICE("price"),
    SEATS_NUMBER("seats number"),
    FREE_SEATS("free seats"),
    DATE("date"),
    COMPLETE("Command completed\n"),
    COMMAND("command"),
    DAY("day"),
    MONTH("month"),
    YEAR("year"),
    NEW_LINE("\n"),
    SUCCESS("Success operation\n"),
    SAVE_ERROR("Save error"),
    HELP("\t1) help\n" +
        "\t2) exit\n" +
        "\t3) show all\n" +
        "\t4) show tours by operator name\n" +
        "\t5) show tours by visit points\n" +
        "\t6) show current tours\n" +
        "\t7) add tour\n" +
        "\t8) delete tour\n");

    private final String message;

    Dictionary(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
