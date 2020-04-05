import React from 'react';
import {Edit, required, SimpleForm, TextInput} from 'react-admin';

export const VocabularyEdit = (props) => {
    console.info('VocabularyEdit:', props);
    return (
        <Edit {...props} title={`${props.options.label}#${props.id}`}>
            <SimpleForm>
                <TextInput label={'名称'} source="name" validate={required()}/>
                <TextInput label={'解释'} source="explanation" validate={required()}/>
            </SimpleForm>
        </Edit>
    );
};
