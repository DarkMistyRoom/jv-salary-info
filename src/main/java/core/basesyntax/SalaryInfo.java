package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static  final int HOURS_INDEX = 2;
    static  final int INCOME_INDEX = 3;

    public String getSalaryInfo(
            String[] names,
            String[] data,
            String dateFrom,
            String dateTo
    ) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (String name: names) {
            int salary = 0;

            for (String record: data) {
                String[] splitRecord = record.split(" ");
                String localName = splitRecord[NAME_INDEX];
                LocalDate date = LocalDate.parse(splitRecord[DATE_INDEX], FORMATTER);

                if (localName.equals(name)
                        && (date.isAfter(fromDate) || date.equals(fromDate))
                        && (date.isBefore(toDate) || date.equals(toDate))) {
                    int hours = Integer.parseInt(splitRecord[HOURS_INDEX]);
                    int income = Integer.parseInt(splitRecord[INCOME_INDEX]);
                    salary += hours * income;
                }
            }

            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return result.toString();
    }
}
