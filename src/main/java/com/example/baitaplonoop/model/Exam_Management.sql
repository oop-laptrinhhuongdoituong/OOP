    CREATE Database Exam_Management2
    GO
    Use Exam_Management2

    Go

    Create table Category
    (
        parentID nvarchar(10),
        categoryName nvarchar(50),
        categoryID nvarchar(10) primary key,
        categoryinfo nvarchar(1000)

    )
    GO

    Create table Quiz
    (
        quizName nvarchar(500) primary key,
        Descript nvarchar(4000),
        openTime datetime,
        closeTime datetime,
        timeLimit int,
        mark float,
    )
     Go
     Create table Question
     (
        categoryID nvarchar(10),
        questionID nvarchar(10) primary key,
        questionText nvarchar(4000),
        questionMedia nvarchar(100),
        questionMark float,
        Foreign key(categoryID) references Category(categoryID)
     )
     go

     Create table QuestionInQuiz
     (
        questionID nvarchar(10),
        quizName nvarchar(500),
        yourMark float,
        Foreign key(questionID) references Question(questionID),
        Foreign key(quizName) references Quiz(quizName)
     )
     go

     Create table Choice
     (
        choiceText nvarchar(4000) primary key,
        choiceGrade float,
        choiceID nvarchar(12),
        questionID nvarchar(10),
        isSelected bit,
        choiceMedia nvarchar(100),
        Foreign key(questionID) references Question(questionID),

     )

     Go

