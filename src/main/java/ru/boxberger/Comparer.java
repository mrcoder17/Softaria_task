package ru.boxberger;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Comparer {
    private final Map<String, String> pairsToday;
    private final Map<String, String> pairsYesterday;

    private Set<String> disappeared;
    private Set<String> appeared;
    private Set<String> changed;

    public Comparer(Map<String, String> pairsToday, Map<String, String> pairsYesterday) {
        this.pairsToday = pairsToday;
        this.pairsYesterday = pairsYesterday;
        this.detectingChanges();
    }

    private void detectingChanges() {
        disappeared = new HashSet<>(pairsYesterday.keySet());
        disappeared.removeAll(pairsToday.keySet());

        appeared = new HashSet<>(pairsToday.keySet());
        appeared.removeAll(pairsYesterday.keySet());

        changed = new HashSet<>();
        for (String key : pairsYesterday.keySet()) {
            if (pairsToday.containsKey(key) && !pairsToday.get(key).equals(pairsYesterday.get(key))) {
                changed.add(key);
            }
        }
    }

    public String createMessage() {
        StringBuilder sb = new StringBuilder();

        sb.append("Здравствуйте, дорогая и.о. секретаря\n\n");
        sb.append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");

        appendChanges(sb, "Исчезли следующие страницы:", disappeared);
        appendChanges(sb, "Появились следующие новые страницы:", appeared);
        appendChanges(sb, "Изменились следующие страницы:", changed);

        sb.append("С уважением, \nавтоматизированная система мониторинга.\n");

        return sb.toString();
    }

    private void appendChanges(StringBuilder sb, String s, Set<String> changes) {
        if (!changed.isEmpty()) {
            sb.append(s).append("\n");
            sb.append(String.join("\n", changes)).append("\n");
        }
    }

}