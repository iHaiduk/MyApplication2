<?php

/**
 * This is the model class for table "question".
 *
 * The followings are the available columns in table 'question':
 * @property integer $id
 * @property integer $uid_test
 * @property string $question
 * @property string $answ1
 * @property string $answ2
 * @property string $answ3
 * @property string $answ4
 * @property integer $answtrue
 */
class Question extends CActiveRecord
{
	/**
	 * @return string the associated database table name
	 */
	public function tableName()
	{
		return 'question';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('uid_test, question, answ1, answ2, answ3, answ4, answtrue', 'required'),
			array('uid_test, answtrue', 'numerical', 'integerOnly'=>true),
			array('answ1, answ2, answ3, answ4', 'length', 'max'=>255),
			// The following rule is used by search().
			// @todo Please remove those attributes that should not be searched.
			array('id, uid_test, question, answ1, answ2, answ3, answ4, answtrue', 'safe', 'on'=>'search'),
		);
	}

	/**
	 * @return array relational rules.
	 */
	public function relations()
	{
		// NOTE: you may need to adjust the relation name and the related
		// class name for the relations automatically generated below.
        return array(
            'test' => array(self::BELONGS_TO, 'Test', 'uid_test')
        );
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'id' => 'ID',
			'uid_test' => 'Uid Test',
			'question' => 'Question',
			'answ1' => 'Answ1',
			'answ2' => 'Answ2',
			'answ3' => 'Answ3',
			'answ4' => 'Answ4',
			'answtrue' => 'Answtrue',
		);
	}

	/**
	 * Retrieves a list of models based on the current search/filter conditions.
	 *
	 * Typical usecase:
	 * - Initialize the model fields with values from filter form.
	 * - Execute this method to get CActiveDataProvider instance which will filter
	 * models according to data in model fields.
	 * - Pass data provider to CGridView, CListView or any similar widget.
	 *
	 * @return CActiveDataProvider the data provider that can return the models
	 * based on the search/filter conditions.
	 */
	public function search()
	{
		// @todo Please modify the following code to remove attributes that should not be searched.

		$criteria=new CDbCriteria;

		$criteria->compare('id',$this->id);
		$criteria->compare('uid_test',$this->uid_test);
		$criteria->compare('question',$this->question,true);
		$criteria->compare('answ1',$this->answ1,true);
		$criteria->compare('answ2',$this->answ2,true);
		$criteria->compare('answ3',$this->answ3,true);
		$criteria->compare('answ4',$this->answ4,true);
		$criteria->compare('answtrue',$this->answtrue);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}

	/**
	 * Returns the static model of the specified AR class.
	 * Please note that you should have this exact method in all your CActiveRecord descendants!
	 * @param string $className active record class name.
	 * @return Question the static model class
	 */
	public static function model($className=__CLASS__)
	{
		return parent::model($className);
	}
}
