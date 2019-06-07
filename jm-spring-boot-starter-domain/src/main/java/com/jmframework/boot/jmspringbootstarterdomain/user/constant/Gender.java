package com.jmframework.boot.jmspringbootstarterdomain.user.constant;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>Gender</h1>
 * <p>Gender enum</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-07 23:17
 * @see
 * <a href="https://abcnews.go.com/blogs/headlines/2014/02/heres-a-list-of-58-gender-options-for-facebook-users/">Here's
 * a List of 58 Gender Options for Facebook Users</a>
 * @see <a href="https://gender.wikia.org/">Gender Wiki</a>
 **/
@Slf4j
@Getter
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public enum Gender {
    /**
     * Agender: Without gender
     */
    AGENDER("Agender", "Without gender"),
    /**
     * Androgyne: A non-binary gender identity associated with androgyny
     */
    ANDROGYNE("Androgyne", "A non-binary gender identity associated with androgyny"),
    /**
     * Bigender: A gender identity which can be literally translated as 'two genders' or 'double gender'
     */
    BIGENDER("Bigender", "A gender identity which can be literally translated as 'two genders' or 'double gender'"),
    /**
     * Cisgender: A term for someone who has a gender identity that aligns with what they were assigned at birth
     */
    CISGENDER("Cisgender",
              "A term for someone who has a gender identity that aligns with what they were assigned at birth"),
    /**
     * Cisgender Female: Cisgender female
     */
    CISGENDER_FEMALE("Cisgender Female", "Cisgender female"),
    /**
     * Cisgender Male: Cisgender male
     */
    CISGENDER_MALE("Cisgender Male", "Cisgender male"),
    /**
     * Female to Male: Female to male
     */
    FEMALE_TO_MALE("Female to Male", "Female to male"),
    /**
     * Gender Fluid: A gender identity which refers to a gender which varies over time
     */
    GENDER_FLUID("Gender Fluid", "A gender identity which refers to a gender which varies over time"),
    /**
     * Gender Nonconforming: Gender nonconforming
     */
    GENDER_NONCONFORMING("Gender Nonconforming", "Gender nonconforming"),
    /**
     * Gender Questioning: Gender questioning
     */
    GENDER_QUESTIONING("Gender Questioning", "Gender questioning"),
    /**
     * Gender Variant: Behavior or gender expression by an individual that does not match masculine and feminine gender
     * norms
     */
    GENDER_VARIANT("Gender Variant",
                   "Behavior or gender expression by an individual that does not match masculine and feminine gender " +
                           "norms"),
    /**
     * Genderqueer: An umbrella term with a similar meaning to non-binary
     */
    GENDERQUEER("Genderqueer", "An umbrella term with a similar meaning to non-binary"),
    /**
     * Intersex: An intersex person has sex characteristics e.g.sexual anatomy, reproductive organs, and/or chromosome
     * patterns that do not fit the typical definition of male or female
     */
    INTERSEX("Intersex",
             "An intersex person has sex characteristics e.g.sexual anatomy, reproductive organs, and/or chromosome " +
                     "patterns that do not fit the typical definition of male or female"),
    /**
     * Male to Female: Male to female
     */
    MALE_TO_FEMALE("Male to Female", "Male to female"),
    /**
     * Neither: Neither
     */
    NEITHER("Neither", "Neither"),
    /**
     * Neutrois: "A non-binary gender identity which is often associated with a 'neutral' or 'null' gender
     */
    NEUTROIS("Neutrois", "A non-binary gender identity which is often associated with a 'neutral' or 'null' gender"),
    /**
     * Non-binary: Any gender identity which does not fit the male and female binary
     */
    NON_BINARY("Non-binary", "Any gender identity which does not fit the male and female binary"),
    /**
     * Other: Other gender
     */
    OTHER("Other", "Other gender"),
    /**
     * Pangender: A non-binary gender identity which refers to a vast and diverse multiplicity of genders in the same
     * individual that can extend infinitely, always within the person's own culture and life experience
     */
    PANGENDER("Pangender",
              "A non-binary gender identity which refers to a vast and diverse multiplicity of genders in the same " +
                      "individual that can extend infinitely, always within the person's own culture and life " +
                      "experience"),
    /**
     * Transfeminine: A term used to describe transgender people who were assigned male at birth, but identify with
     * femininity to a greater extent than with masculinity
     */
    TRANSFEMININE("Transfeminine",
                  "A term used to describe transgender people who were assigned male at birth, but identify with " +
                          "femininity to a greater extent than with masculinity"),
    /**
     * Transgender: An umbrella term for anyone whose internal experience of gender does not match the gender they were
     * assigned at birth
     */
    TRANSGENDER("Transgender",
                "An umbrella term for anyone whose internal experience of gender does not match the gender they were " +
                        "assigned at birth"),
    /**
     * Transgender Female: Transgender female
     */
    TRANSGENDER_FEMALE("Transgender Female", "Transgender female"),
    /**
     * Transgender Male: Transgender male
     */
    TRANSGENDER_MALE("Transgender Male", "Transgender male"),
    /**
     * Transgender Person: Transgender person
     */
    TRANSGENDER_PERSON("Transgender Person", "Transgender person"),
    /**
     * Transmasculine: A term used to describe transgender people who were assigned female at birth, but identify with
     * masculinity to a greater extent than with femininity
     */
    TRANSMASCULINE("Transmasculine",
                   "A term used to describe transgender people who were assigned female at birth, but identify with " +
                           "masculinity to a greater extent than with femininity"),
    /**
     * Two-Spirit: A culturally distinct gender that describes Indigenous North Americans who fulfils one of many mixed
     * gender roles found traditionally among many Native Americans and Canadian First Nations indigenous groups
     */
    TWO_SPIRIT("Two-Spirit",
               "A culturally distinct gender that describes Indigenous North Americans who fulfils one of many mixed " +
                       "gender roles found traditionally among many Native Americans and Canadian First Nations " +
                       "indigenous groups");
    /**
     * Gender value
     */
    private String value;
    /**
     * Gender description
     */
    private String description;

    Gender(String value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Get gender value list
     *
     * @return gender value list
     */
    public static List<String> getValueList() {
        List<Gender> genderList = Arrays.asList(Gender.values());
        return genderList.stream().map(Gender::getValue).collect(Collectors.toList());
    }

    /**
     * Validate a given gender value
     *
     * @param value given gender value
     * @return true - valid: false - invalid
     */
    public static boolean validate(String value) {
        Gender[] genderList = Gender.values();
        Gender targetGender = null;
        for (Gender gender : genderList) {
            if (gender.value.equals(value)) {
                targetGender = gender;
            }
        }
        return targetGender != null;
    }
}
