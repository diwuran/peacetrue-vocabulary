import React from 'react';
import {Create, required, SimpleForm, TextInput,} from 'react-admin';

export const VocabularyCreate = (props) => {
    console.info('VocabularyCreate:', props);
    return (
        <Create {...props} title={`新建${props.options.label}`}>
            <SimpleForm>
                <TextInput label={'名称'} source="name" validate={required()}/>
                <TextInput label={'解释'} source="explanation" validate={required()}/>
            </SimpleForm>
        </Create>
    );
};
