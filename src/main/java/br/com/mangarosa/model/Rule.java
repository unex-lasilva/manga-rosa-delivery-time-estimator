package br.com.mangarosa.model;

import java.util.Objects;

public final class Rule implements Comparable<Rule> {

    private Integer ruleId;

    public Rule(Integer ruleId) {
        setRuleId(ruleId);
    }

    public Rule() {
        // Construtor sem argumentos (opcional)
    }

    @Override
    public int compareTo(Rule o) {
        return this.ruleId.compareTo(o.getRuleId());
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        if (ruleId == null || ruleId < 0) {
            throw new IllegalArgumentException("Rule ID cannot be null or negative");
        }
        this.ruleId = ruleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rule rule)) return false;
        return Objects.equals(ruleId, rule.ruleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleId);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ruleId=" + ruleId +
                '}';
    }
}
