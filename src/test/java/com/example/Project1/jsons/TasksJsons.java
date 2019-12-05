package com.example.Project1.jsons;

public class TasksJsons {
    public static String allTasksJson=
            "[    " +
                    "{        \"id\": 1," +                    "        \"title\": \"Task1\"," +
                    "        \"desc\": \"Το πρώτο Task\"," +                    "        \"difficulty\": \"MEDIUM\"," +
                    "        \"taskStatus\": \"NEW\"    " +                    "}, " +                 "   {" +
                    "        \"id\": 2," +                    "        \"title\": \"Task2\"," +
                    "        \"desc\": \"Το δεύτερο Task\"," +                    "        \"difficulty\": \"MEDIUM\"," +
                    "        \"taskStatus\": \"NEW\"    " +                    "}, " +
                 "   {" +                    "        \"id\": 3," +                    "        \"title\": \"Task3\"," +
                    "        \"desc\": \"Το τρίτο Task\"," +                    "        \"difficulty\": \"MEDIUM\"," +
                    "        \"taskStatus\": \"NEW\"    " +                    "}, " +
                 "   {" +                    "        \"id\": 4," +                    "        \"title\": \"Task4\"," +
                    "        \"desc\": \"Το τέταρτο Task\"," +                    "        \"difficulty\": \"MEDIUM\"," +
                    "        \"taskStatus\": \"NEW\"    " +                    "}, " +                 "   {" +
                    "        \"id\": 5," +                    "        \"title\": \"Task7\"," +
                    "        \"desc\": \"Το έβδομο Task\"," +                    "        \"difficulty\": \"MEDIUM\"," +
                    "        \"taskStatus\": \"NEW\"    " +                    "},    {        \"id\": 6,      " +
                    "  \"title\": \"Task6\",     " +                    "   \"desc\": \"Το έκτο Task\",  " +
                    "      \"difficulty\": \"MEDIUM\",    " +                    "    \"taskStatus\": \"NEW\"    },    {    " +
                    "    \"id\": 7,        \"title\": \"Task8\",     " +                    "   \"desc\": \"Το ογδοο Task\", " +
                    "       \"difficulty\": \"MEDIUM\",    " +
                    "    \"taskStatus\": \"NEW\"    },    {        \"id\": 8,  " +
                    "      \"title\": \"Task9\",        \"desc\": \"Το ένωατο Task\",     " +
                    "   \"difficulty\": \"MEDIUM\",        \"taskStatus\": \"NEW\"    },  " +
                    "  {        \"id\": 9,        \"title\": \"Task10\",        \"desc\": \"Το δέκατο Task\", " +
                    "       \"difficulty\": \"MEDIUM\",        \"taskStatus\": \"NEW\"    }]";

    public static String getTaskById="{    \"id\": 1,    \"title\": \"Task1\",    \"desc\": \"Το πρώτο Task\",    \"difficulty\": \"MEDIUM\",    \"taskStatus\": \"NEW\",  " +
            "  \"assignedEmployees\": [        {            \"id\": 1,            \"recordNumber\": 12345,            \"fullName\": \"James Brown\",          " +
            "  \"telephone\": \"6965455545\",            \"workingPeriod\": \"02/05/2018-23/02/2019\",            \"status\": \"inactive\",            \"contractType\": \"unisystems\",  " +
            "          \"position\": \"Department Manager\",            \"unitName\": \"Unit 3\"        }    ],    \"updates\": [        \"update 1\",        \"update 2\",    " +
            "    \"update 3\",        \"update 4\",        \"update 5\",        \"update 6\",        \"update 7\",        \"update 8\",        \"update 9\",        \"update 10\"    ]}";
    public static String postJson="{      \"title\": \"Task10\",    \"desc\": \"Το δέκατο Task\",    \"estimationA\": \"3\",    \"estimationB\": \"2\",    \"estimationC\": \"1\", " +
            "   \"taskStatus\": \"NEW\",    \"updates\": [          \"update 1\",          \"update 2\",          \"update 3\",          \"update 4\",          \"update 5\",      " +
            "    \"update 6\",          \"update 7\",          \"update 8\",          \"update 9\",          \"update 10\"        ],        \"employees\":[        	{\"id\":6},     " +
            "   	{\"id\":7},        	{\"id\":8}        	]}";


    public static String createdTaskJson="{    \"id\": 10,    \"title\": \"Task10\",    \"desc\": \"Το δέκατο Task\",    \"estimationA\": 3,    \"estimationB\": 2,    \"estimationC\": 1, " +
            "   \"taskStatus\": \"NEW\",    \"updates\": [        \"update 1\",        \"update 2\",        \"update 3\",        \"update 4\",        \"update 5\",        \"update 6\",   " +
            "     \"update 7\",        \"update 8\",        \"update 9\",        \"update 10\"    ],    \"employees\": [        {            \"id\": 6,            \"recordNumber\": 14144, " +
            "           \"lastName\": \"Monkey\",            \"firstName\": \"Luffy\",            \"homeAddress\": \"Blue Lagoon St. 122\",            \"telephoneNo\": \"6972234447\",  " +
            "          \"hireDate\": \"2013-06-15\",            \"leaveDate\": null,            \"status\": \"ACTIVE\",            \"contractType\": \"UNISYSTEMS\",        " +
            "    \"company\": {                \"id\": 1,                \"name\": \"Unisystems\"            },            \"businessUnit\": {                \"id\": 2,         " +
            "       \"name\": \"Business Unit 2\",                \"company\": {                    \"id\": 1,                    \"name\": \"Unisystems\"                }       " +
            "     },            \"department\": {                \"id\": 2,                \"name\": \"HR2\",                \"businessUnit\": {                    \"id\": 2,                    \"name\": \"Business Unit 2\",                    \"company\": {                        \"id\": 1,                        \"name\": \"Unisystems\"                    }                }            },            \"unit\": {                \"id\": 3,                \"name\": \"Unit 3\",                \"department\": {                    \"id\": 2,                    \"name\": \"HR2\",                    \"businessUnit\": {                        \"id\": 2,                        \"name\": \"Business Unit 2\",                        \"company\": {                            \"id\": 1,                            \"name\": \"Unisystems\"                        }                    }                }            },            \"position\": \"Junior Software Developer\",            \"unitName\": \"Unit 3\",            \"businessUnitName\": \"Business Unit 2\",            \"companyName\": \"Unisystems\",            \"departmentName\": \"HR2\"        },        {            \"id\": 7,            \"recordNumber\": 12323,            \"lastName\": \"Roronoa\",            \"firstName\": \"Zoro\",            \"homeAddress\": \"Blue Lagoon St. 121\",            \"telephoneNo\": \"6972235557\",            \"hireDate\": \"2013-06-15\",            \"leaveDate\": null,            \"status\": \"ACTIVE\",            \"contractType\": \"UNISYSTEMS\",            \"company\": {                \"id\": 1,                \"name\": \"Unisystems\"            },            \"businessUnit\": {                \"id\": 2,                \"name\": \"Business Unit 2\",                \"company\": {                    \"id\": 1,                    \"name\": \"Unisystems\"                }            },            \"department\": {                \"id\": 2,                \"name\": \"HR2\",                \"businessUnit\": {                    \"id\": 2,                    \"name\": \"Business Unit 2\",                    \"company\": {                        \"id\": 1,                        \"name\": \"Unisystems\"                    }                }            },            \"unit\": {                \"id\": 3,                \"name\": \"Unit 3\",                \"department\": {                    \"id\": 2,                    \"name\": \"HR2\",                    \"businessUnit\": {                        \"id\": 2,                        \"name\": \"Business Unit 2\",                        \"company\": {                            \"id\": 1,                            \"name\": \"Unisystems\"                        }                    }                }            },            \"position\": \"Senior Software Developer\",            \"unitName\": \"Unit 3\",            \"businessUnitName\": \"Business Unit 2\",            \"companyName\": \"Unisystems\",            \"departmentName\": \"HR2\"        },        {            \"id\": 8,            \"recordNumber\": 98742,            \"lastName\": \"HawkEye\",            \"firstName\": \"Mihawk\",            \"homeAddress\": \"Blue Lagoon St. 123\",            \"telephoneNo\": \"6972666587\",            \"hireDate\": \"2013-06-15\",            \"leaveDate\": null,            \"status\": \"ACTIVE\",            \"contractType\": \"UNISYSTEMS\",            \"company\": {                \"id\": 1,                \"name\": \"Unisystems\"            },            \"businessUnit\": {                \"id\": 2,                \"name\": \"Business Unit 2\",                \"company\": {                    \"id\": 1,                    \"name\": \"Unisystems\"                }            },            \"department\": {                \"id\": 2,                \"name\": \"HR2\",                \"businessUnit\": {                    \"id\": 2,                    \"name\": \"Business Unit 2\",                    \"company\": {                        \"id\": 1,                        \"name\": \"Unisystems\"                    }                }            },            \"unit\": {                \"id\": 3,                \"name\": \"Unit 3\",                \"department\": {                    \"id\": 2,                    \"name\": \"HR2\",                    \"businessUnit\": {                        \"id\": 2,                        \"name\": \"Business Unit 2\",                        \"company\": {                            \"id\": 1,                            \"name\": \"Unisystems\"                        }                    }                }            },            \"position\": \"Master Software Developer\",            \"unitName\": \"Unit 3\",            \"businessUnitName\": \"Business Unit 2\",            \"companyName\": \"Unisystems\",            \"departmentName\": \"HR2\"        }    ]}";
    public static String updateTaskJson="{    \"estimationC\": \"2\",    \"updates\": [          \"update 11\"        ]}";
    public static String updatedTaskJson="{    \"id\": 1,    \"title\": \"Task1\",    \"desc\": \"Το πρώτο Task\",    \"estimationA\": 4,    \"estimationB\": 5,    \"estimationC\": 2,  " +
            "  \"taskStatus\": \"NEW\",    \"updates\": [        \"update 11\"    ],    \"employees\": [        {            \"id\": 1,            \"recordNumber\": 12345,      " +
            "      \"lastName\": \"Brown\",            \"firstName\": \"James\",            \"homeAddress\": \"Main St. 134\",            \"telephoneNo\": \"6965455545\",      " +
            "      \"hireDate\": \"2018-05-02\",            \"leaveDate\": \"2019-02-23\",            \"status\": \"INACTIVE\",            \"contractType\": \"UNISYSTEMS\",     " +
            "       \"company\": {                \"id\": 1,                \"name\": \"Unisystems\"            },            \"businessUnit\": {                \"id\": 2,       " +
            "         \"name\": \"Business Unit 2\",                \"company\": {                    \"id\": 1,                    \"name\": \"Unisystems\"                }      " +
            "      },            \"department\": {                \"id\": 2,                \"name\": \"HR2\",                \"businessUnit\": {                    \"id\": 2,   " +
            "                 \"name\": \"Business Unit 2\",                    \"company\": {                        \"id\": 1,                        \"name\": \"Unisystems\"   " +
            "                 }                }            },            \"unit\": {                \"id\": 3,                \"name\": \"Unit 3\",                \"department\": {   " +
            "                 \"id\": 2,                    \"name\": \"HR2\",                    \"businessUnit\": {                        \"id\": 2,                  " +
            "      \"name\": \"Business Unit 2\",                        \"company\": {                            \"id\": 1,                            \"name\": \"Unisystems\"  " +
            "                      }                    }                }            },            \"position\": \"Department Manager\",            \"unitName\": \"Unit 3\",       " +
            "     \"companyName\": \"Unisystems\",            \"departmentName\": \"HR2\",            \"businessUnitName\": \"Business Unit 2\"        }    ]}";

}
