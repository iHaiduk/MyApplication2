<?php

class QuestionController extends Controller
{
	/**
	 * Displays a particular model.
	 * @param integer $id the ID of the model to be displayed
	 */
	public function actionView($id)
	{
        $array = array();
        $test = Test::model()->findByPk($id);
        $model = $this->loadModel($id);

        foreach($model as $key=>$val){
            $array[] = array(
                "id"=>$val->id,
                "question"=>$val->question,
                "uid_test"=>$id,
                "answ1"=>$val->answ1,
                "answ2"=>$val->answ2,
                "answ3"=>$val->answ3,
                "answ4"=>$val->answ4
            );
        }

        echo json_encode(array("name"=>$test->name, "question"=>$array));
	}

	/**
	 * Creates a new model.
	 * If creation is successful, the browser will be redirected to the 'view' page.
	 */
    public function actionCreate()
    {
        $model=new Question;
        $model->answ1 = $_POST["answer1"];
        $model->answ2 = $_POST["answer2"];
        $model->answ3 = $_POST["answer3"];
        $model->answ4 = $_POST["answer4"];
        $model->answtrue = $_POST["value"];
        $model->uid_test = $_POST["id_question"];
        $model->question = $_POST["textQuestion"];
        echo json_encode(array("result"=>$model->save(), "id"=>$model->id));
    }
    public function actionAll()
    {
        $model = Question::model()->findAll();
        echo CJSON::encode($model);
    }

	/**
	 * Updates a particular model.
	 * If update is successful, the browser will be redirected to the 'view' page.
	 * @param integer $id the ID of the model to be updated
	 */
	public function actionUpdate($id)
	{
		$model=$this->loadModel($id);

		// Uncomment the following line if AJAX validation is needed
		// $this->performAjaxValidation($model);

		if(isset($_POST['Question']))
		{
			$model->attributes=$_POST['Question'];
			if($model->save())
				$this->redirect(array('view','id'=>$model->id));
		}

		$this->render('update',array(
			'model'=>$model,
		));
	}

	/**
	 * Deletes a particular model.
	 * If deletion is successful, the browser will be redirected to the 'admin' page.
	 * @param integer $id the ID of the model to be deleted
	 */
	public function actionDelete($id)
	{
		$this->loadModel($id)->delete();

		// if AJAX request (triggered by deletion via admin grid view), we should not redirect the browser
		if(!isset($_GET['ajax']))
			$this->redirect(isset($_POST['returnUrl']) ? $_POST['returnUrl'] : array('admin'));
	}

	/**
	 * Lists all models.
	 */
	public function actionIndex()
	{
		$dataProvider=new CActiveDataProvider('Question');
		$this->render('index',array(
			'dataProvider'=>$dataProvider,
		));
	}

	/**
	 * Manages all models.
	 */
	public function actionAdmin()
	{
		$model=new Question('search');
		$model->unsetAttributes();  // clear any default values
		if(isset($_GET['Question']))
			$model->attributes=$_GET['Question'];

		$this->render('admin',array(
			'model'=>$model,
		));
	}

	/**
	 * Returns the data model based on the primary key given in the GET variable.
	 * If the data model is not found, an HTTP exception will be raised.
	 * @param integer $id the ID of the model to be loaded
	 * @return Question the loaded model
	 * @throws CHttpException
	 */
	public function loadModel($id)
	{
		$model=Question::model()->findAllByAttributes(
        array("uid_test"=>$id));
		return $model;
	}
}
